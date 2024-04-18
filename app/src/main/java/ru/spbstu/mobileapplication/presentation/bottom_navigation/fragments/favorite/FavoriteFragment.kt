package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.favorite

import android.content.Context
import android.os.Bundle
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
import ru.spbstu.mobileapplication.databinding.FragmentFavoriteBinding
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.enums.FeedbackType
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.AnnouncementAdapter
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnDislikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnLikeClickListener
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.listener.OnSkipClickListener
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment(), OnLikeClickListener, OnDislikeClickListener,
    OnSkipClickListener {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoriteBinding is null")

    private var isLoading = false

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

        val loadingIndicator = binding.loadingIndicator

        lifecycleScope.launch(Dispatchers.IO) {
            val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            try {
                loadingIndicator.visibility = View.VISIBLE
                loadAnnouncements(10, 0, token)
            } catch (e: Exception) {
                Log.e(TAG, "Error in network", e)
            } finally {
                loadingIndicator.visibility = View.GONE
            }
        }

        Log.d(TAG, "FavoriteFragment onViewCreated")
    }

    private suspend fun loadAnnouncements(limit: Int, offset: Int, token: String) {
        if (isLoading) return
        isLoading = true
        val announcements = viewModel.getFavoriteAnnouncements(limit, offset, token).toMutableList()

        if (announcements.isEmpty()) {
            Log.d(TAG, "No more announcements to load")
            return
        }

        withContext(Dispatchers.Main) {
            val favoriteAdapter = FavoriteAdapter(
                announcements,
                this@FavoriteFragment,
                this@FavoriteFragment,
            )
            binding.rvAnnouncementList.adapter = favoriteAdapter

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
        }

        binding.rvAnnouncementList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val newOffset = offset + 1
                    lifecycleScope.launch {
                        loadAnnouncements(limit, newOffset, token)
                    }
                }
            }
        })
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

    private fun handleFeedbackClick(position: Int, feedbackType: FeedbackType) {
        val announcementAdapter = binding.rvAnnouncementList.adapter as FavoriteAdapter
        val announcement = announcementAdapter.announcements[position]
        val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
        val feedbackCreateEntity = FeedbackCreateEntity(feedbackType, announcement.id)

        lifecycleScope.launch {
            viewModel.sendRequest(feedbackCreateEntity, token)

            when (feedbackType) {
                FeedbackType.LIKE -> {
                    val updatedAnnouncement = announcement.copy(isLikedByUser = true)
                    announcementAdapter.updateAnnouncement(position, updatedAnnouncement)
                }

                FeedbackType.SKIP -> {
                    // Убирает данное объявление на 2 недели из предложений для пользователя
                    announcementAdapter.deleteAnnouncement(position)
                }

                FeedbackType.DISLIKE -> {
                    // Убирает данное объявление навсегда из предложений для пользователя
                    announcementAdapter.deleteAnnouncement(position)
                }
            }
        }
    }

    private companion object {
        private const val TAG = "FavoriteFragment"
    }
}