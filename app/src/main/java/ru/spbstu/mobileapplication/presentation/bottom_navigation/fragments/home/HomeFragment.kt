package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.databinding.FragmentHomeBinding
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.GetScrollPositionUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.SaveAnnouncementIdUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.SaveScrollPositionUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.SaveTagUseCase
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.enums.FeedbackType
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDefaultClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnSkipClickListener
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), OnLikeClickListener, OnDislikeClickListener, OnSkipClickListener,
    OnDefaultClickListener {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var saveAnnouncementIdUseCase: SaveAnnouncementIdUseCase

    @Inject
    lateinit var saveTagUseCase: SaveTagUseCase

    @Inject
    lateinit var saveScrollPositionUseCase: SaveScrollPositionUseCase

    @Inject
    lateinit var getScrollPositionUseCase: GetScrollPositionUseCase

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    private lateinit var token: String
    private lateinit var lastSurvey: AnswerDbModel

    private var isLoading = false
    private var currentOffset = 0

    private var layoutManagerState: Parcelable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "HomeFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        Log.d(TAG, "HomeFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "HomeFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setObservers()

        swipeHandler()

        if (viewModel.announcements.value.isNullOrEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
                lastSurvey = viewModel.getLastSurveyFromDB()
                loadAnnouncements()
            }
        }

        val localStorageScrollPosition = getScrollPositionUseCase()
        if (localStorageScrollPosition != 0) {
            val layoutManager = binding.rvAnnouncementList.layoutManager as? LinearLayoutManager
            layoutManager?.scrollToPosition(localStorageScrollPosition)
        }

        Log.d(TAG, savedInstanceState.toString())
        if (savedInstanceState != null) {
            val scrollPosition = savedInstanceState.getInt("scrollPosition", 0)
            Log.d(TAG, scrollPosition.toString())
            val layoutManager = binding.rvAnnouncementList.layoutManager as? LinearLayoutManager
            layoutManager?.scrollToPosition(scrollPosition)
        }

        Log.d(TAG, "HomeFragment onViewCreated")
    }

    private fun setObservers() {
        isCallObserver()
        isLoadingObserve()
        selectedAnnouncementIdObserver()
        announcementsObserver()
    }

    private fun isCallObserver() {
        viewModel.intentFlow.filterNotNull().asLiveData().observe(viewLifecycleOwner) { intent ->
            startActivity(intent)
        }
    }

    private fun selectedAnnouncementIdObserver() {
        viewModel.selectedAnnouncementId.observe(viewLifecycleOwner) { announcementId ->
            announcementId?.let {
                saveIntoLocalStorage(announcementId)
                navigateToAnnouncementDetails()
            }
        }
    }

    private fun isLoadingObserve() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            Log.d(TAG, "isLoading observer")
            binding.loadingIndicator.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun announcementsObserver() {
        viewModel.announcements.observe(viewLifecycleOwner) {
            binding.rvAnnouncementList.layoutManager?.onRestoreInstanceState(layoutManagerState)

            val adapter = AnnouncementAdapter(
                it,
                viewModel,
                this@HomeFragment,
                this@HomeFragment,
                this@HomeFragment,
                this@HomeFragment
            )
            binding.rvAnnouncementList.adapter = adapter
        }
    }

    private fun saveIntoLocalStorage(announcementId: Int) {
        saveAnnouncementIdUseCase(announcementId)
        saveTagUseCase(TAG)
    }

    private fun navigateToAnnouncementDetails() {
        val layoutManager = binding.rvAnnouncementList.layoutManager as? LinearLayoutManager
        saveScrollPositionUseCase(layoutManager?.findFirstVisibleItemPosition() ?: 0)
        findNavController().navigate(R.id.action_navigation_home_to_announcementDetailsFragment)
    }

    override fun onItemSkip(position: Int) {
        handleFeedbackClick(position, FeedbackType.SKIP)
    }

    override fun onItemLike(position: Int) {
        handleFeedbackClick(position, FeedbackType.LIKE)
    }

    override fun onItemDislike(position: Int) {
        handleFeedbackClick(position, FeedbackType.DISLIKE)
    }

    override fun onItemDefault(position: Int) {
        handleFeedbackClick(position, FeedbackType.DEFAULT)
    }

    private suspend fun loadAnnouncements() {
        if (isLoading) {
            Log.d(TAG, "Already loading, skip loading")
            return
        }
        isLoading = true
        layoutManagerState = binding.rvAnnouncementList.layoutManager?.onSaveInstanceState()

        viewModel.getAnnouncements(lastSurvey, currentOffset, token)
        if (viewModel.announcements.value?.isEmpty() == true) {
            Log.d(TAG, "No more announcements to load")
            isLoading = false
            return
        }
        withContext(Dispatchers.Main) {
            scrollHandler()
        }
    }


    private fun scrollHandler() {
        binding.rvAnnouncementList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val newOffset = currentOffset + 1
                    currentOffset = newOffset
                    lifecycleScope.launch(Dispatchers.IO) {
                        loadAnnouncements()
                    }
                }
            }
        })
        isLoading = false
    }

    private fun swipeHandler() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onItemSkip(viewHolder.bindingAdapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvAnnouncementList)
    }

    private fun handleFeedbackClick(position: Int, feedbackType: FeedbackType) {
        val announcementAdapter = binding.rvAnnouncementList.adapter as AnnouncementAdapter
        val announcement = announcementAdapter.announcements[position]
        val feedbackCreateEntity = FeedbackCreateEntity(feedbackType, announcement.id)

        lifecycleScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Sending feedback for announcement with id=${announcement.id}")
            viewModel.createFeedback(feedbackCreateEntity, token)

            withContext(Dispatchers.Main) {
                when (feedbackType) {
                    FeedbackType.LIKE -> {
                        val updatedAnnouncement = announcement.copy(isLikedByUser = true)
                        announcementAdapter.updateAnnouncement(position, updatedAnnouncement)
                    }

                    FeedbackType.SKIP, FeedbackType.DISLIKE -> {
                        announcementAdapter.deleteAnnouncement(position)
                    }

                    FeedbackType.DEFAULT -> {
                        val updatedAnnouncement = announcement.copy(isLikedByUser = false)
                        announcementAdapter.updateAnnouncement(position, updatedAnnouncement)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState called")
        val layoutManager = binding.rvAnnouncementList.layoutManager as? LinearLayoutManager
        outState.putInt("scrollPosition", layoutManager?.findFirstVisibleItemPosition() ?: 0)
    }


    private companion object {
        private const val TAG = "HomeFragment"
    }
}
