package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.favorite

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.spbstu.mobileapplication.databinding.FragmentFavoriteBinding
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.SaveAnnouncementIdUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.SaveTagUseCase
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.enums.FeedbackType
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.AnnouncementAdapter
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDefaultClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnSkipClickListener
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment(), OnLikeClickListener, OnDislikeClickListener,
    OnSkipClickListener, OnDefaultClickListener {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    @Inject
    lateinit var saveAnnouncementIdUseCase: SaveAnnouncementIdUseCase

    @Inject
    lateinit var saveTagUseCase: SaveTagUseCase

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding is null")

    private var isLoading = false
    private var currentOffset = 0

    private lateinit var token: String
    private var layoutManagerState: Parcelable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "FavoriteFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        Log.d(TAG, "FavoriteFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "FavoriteFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        isLoadingObserve()

        viewModel.selectedAnnouncementId.observe(viewLifecycleOwner) { announcementId ->
            announcementId?.let {
                saveIntoLocalStorage(announcementId)
                navigateToAnnouncementDetails()
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            loadAnnouncements()
        }

        Log.d(TAG, "FavoriteFragment onViewCreated")
    }

    private fun isLoadingObserve() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            Log.d(TAG, "isLoading observer")
            binding.loadingIndicator.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private suspend fun loadAnnouncements() {
        if (isLoading) {
            Log.d(TAG, "Already loading, skip loading")
            return
        }
        isLoading = true
        layoutManagerState = binding.rvAnnouncementList.layoutManager?.onSaveInstanceState()

        viewModel.getFavoriteAnnouncements(currentOffset, token)
        if (viewModel.announcements.value?.isEmpty() == true) {
            Log.d(TAG, "No more announcements to load")
            isLoading = false
            return
        }

        withContext(Dispatchers.Main) {
            Log.d(TAG, "Announcements loaded, updating RecyclerView")

            viewModel.announcements.observe(viewLifecycleOwner) {
                binding.rvAnnouncementList.layoutManager?.onRestoreInstanceState(layoutManagerState)

                val adapter =
                    FavoriteAdapter(
                        it,
                        viewModel,
                        this@FavoriteFragment,
                        this@FavoriteFragment,
                        this@FavoriteFragment,
                        this@FavoriteFragment
                    )
                binding.rvAnnouncementList.adapter = adapter
            }

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
                    val position = viewHolder.bindingAdapterPosition
                    val adapter = binding.rvAnnouncementList.adapter as AnnouncementAdapter
                    onItemSkip(position)
                }
            })
            itemTouchHelper.attachToRecyclerView(binding.rvAnnouncementList)

            binding.rvAnnouncementList.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val newOffset = currentOffset + 1
                        currentOffset = newOffset
                        lifecycleScope.launch {
                            loadAnnouncements()
                        }
                    }
                }
            })
            isLoading = false
        }
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

    private fun handleFeedbackClick(position: Int, feedbackType: FeedbackType) {
        val announcementAdapter = binding.rvAnnouncementList.adapter as FavoriteAdapter
        val announcement = announcementAdapter.announcements[position]
        val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
        val feedbackCreateEntity = FeedbackCreateEntity(feedbackType, announcement.id)

        lifecycleScope.launch {
            viewModel.createFeedback(feedbackCreateEntity, token)

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

    private fun saveIntoLocalStorage(announcementId: Int) {
        saveAnnouncementIdUseCase(announcementId)
        saveTagUseCase(TAG)
    }

    private fun navigateToAnnouncementDetails() {
        findNavController().navigate(
            FavoriteFragmentDirections.actionNavigationFavoriteToAnnouncementDetailsFragment()
        )
    }

    private companion object {
        private const val TAG = "FavoriteFragment"
    }
}