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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.databinding.FragmentHomeBinding
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.enums.FeedbackType
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnSkipClickListener
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), OnLikeClickListener, OnDislikeClickListener, OnSkipClickListener {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

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

        isLoadingObserve()

        lifecycleScope.launch(Dispatchers.IO) {
            val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            val model = viewModel.getLastSurveyFromDB()
            loadAnnouncements(model, token)
        }

        Log.d(TAG, "HomeFragment onViewCreated")
    }

    private fun isLoadingObserve() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            Log.d(TAG, "isLoading observer")
            binding.loadingIndicator.visibility = if (it) View.VISIBLE else View.GONE
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

    private suspend fun loadAnnouncements(
        model: AnswerDbModel, token: String, offset: Int = 0
    ) {
        if (isLoading) {
            Log.d(TAG, "Already loading, skip loading")
            return
        }
        isLoading = true
        layoutManagerState = binding.rvShopList.layoutManager?.onSaveInstanceState()

        viewModel.getAnnouncements(lastSurvey = model, offset = currentOffset, token = token)
        if (viewModel.announcements.value?.isEmpty() == true) {
            Log.d(TAG, "No more announcements to load")
            isLoading = false
            return
        }

        withContext(Dispatchers.Main) {
            Log.d(TAG, "Announcements loaded, updating RecyclerView")

            viewModel.announcements.observe(viewLifecycleOwner) {
                binding.rvShopList.layoutManager?.onRestoreInstanceState(layoutManagerState)

                val adapter = AnnouncementAdapter(it, this@HomeFragment, this@HomeFragment)
                binding.rvShopList.adapter = adapter
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
                    val adapter = binding.rvShopList.adapter as AnnouncementAdapter
                    onItemSkip(position)
                }
            })
            itemTouchHelper.attachToRecyclerView(binding.rvShopList)

            binding.rvShopList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val newOffset = currentOffset + 1
                        currentOffset = newOffset
                        lifecycleScope.launch {
                            loadAnnouncements(model, token, newOffset)
                        }
                    }
                }
            })
            isLoading = false
        }
    }

    private fun handleFeedbackClick(position: Int, feedbackType: FeedbackType) {
        val announcementAdapter = binding.rvShopList.adapter as AnnouncementAdapter
        val announcement = announcementAdapter.announcements[position]
        val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
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
                }
            }
        }
    }


    private companion object {
        private const val TAG = "HomeFragment"
    }
}
