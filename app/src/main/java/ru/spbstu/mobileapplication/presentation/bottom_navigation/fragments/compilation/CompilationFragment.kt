package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.databinding.FragmentCompilationBinding
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.enums.FeedbackType
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.CompilationViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CompilationFragment : Fragment(), CardStackListener {
    private val manager by lazy { CardStackLayoutManager(this.context, this) }

    private lateinit var viewModel: CompilationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    private var currentPosition: Int = 0

    private var _binding: FragmentCompilationBinding? = null
    private val binding: FragmentCompilationBinding
        get() = _binding ?: throw RuntimeException("FragmentCompilationBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "CompilationFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompilationBinding.inflate(inflater, container, false)
        Log.d(TAG, "CompilationFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "CompilationFragment onViewCreating")
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CompilationViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        lifecycleScope.launch {
            val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            val model = viewModel.getLastSurveyFromDB()
            try {
                loadAnnouncements(model, token, 10, 0)
            } catch (e: Exception) {
                Log.e(TAG, "Error in network", e)
            }
        }
        Log.d(TAG, "CompilationFragment onViewCreated")
    }

    private suspend fun loadAnnouncements(
        lastSurvey: AnswerDbModel, token: String, limit: Int, offset: Int
    ) {
        val announcements = viewModel.sendRequest(lastSurvey, limit, offset, token).toMutableList()
        if (announcements.isEmpty()) {
            Log.d(TAG, "No more announcements to load")
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            for (announcement in announcements) {
                viewModel.recordIntoDB(announcement)
            }
        }

        val cardStackView = binding.cardStackView

        val adapter = CardStackViewAdapter(announcements)
        cardStackView.adapter = adapter

        setupButtonListeners(adapter, token)
    }

    private fun setupButtonListeners(adapter: CardStackViewAdapter, token: String) {
        val cardStackView = binding.cardStackView

        binding.dislikeButton.setOnClickListener {
            dislikeHandler(adapter, token, cardStackView)
        }

        binding.skipButton.setOnClickListener {
            skipHandler(adapter, token, cardStackView)
        }
        binding.likeButton.setOnClickListener {
            likeHandler(adapter, token, cardStackView)
        }
    }

    private fun likeHandler(
        adapter: CardStackViewAdapter,
        token: String,
        cardStackView: CardStackView
    ) {
        Log.d(TAG, "like clicked")
        val announcement = adapter.announcements[currentPosition]
        val feedbackType = FeedbackType.LIKE
        val feedbackCreateEntity = FeedbackCreateEntity(feedbackType, announcement.id)
        lifecycleScope.launch(Dispatchers.Default) {
            viewModel.sendRequest(feedbackCreateEntity, token)
        }
        val setting = SwipeAnimationSetting.Builder().setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration).setInterpolator(AccelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
        currentPosition++
    }

    private fun skipHandler(
        adapter: CardStackViewAdapter,
        token: String,
        cardStackView: CardStackView
    ) {
        Log.d(TAG, "skip clicked")
        val announcement = adapter.announcements[currentPosition]
        val feedbackType = FeedbackType.SKIP
        val feedbackCreateEntity = FeedbackCreateEntity(feedbackType, announcement.id)
        lifecycleScope.launch(Dispatchers.Default) {
            viewModel.sendRequest(feedbackCreateEntity, token)
        }
        val setting = SwipeAnimationSetting.Builder().setDirection(Direction.Bottom)
            .setDuration(Duration.Normal.duration).setInterpolator(DecelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
        currentPosition++
    }

    private fun dislikeHandler(
        adapter: CardStackViewAdapter,
        token: String,
        cardStackView: CardStackView
    ) {
        Log.d(TAG, "dislike clicked")
        val announcement = adapter.announcements[currentPosition]
        val feedbackType = FeedbackType.DISLIKE
        val feedbackCreateEntity = FeedbackCreateEntity(feedbackType, announcement.id)
        lifecycleScope.launch(Dispatchers.Default) {
            viewModel.sendRequest(feedbackCreateEntity, token)
        }
        val setting = SwipeAnimationSetting.Builder().setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration).setInterpolator(AccelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
        currentPosition++
    }


    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d(TAG, "onCardDragging: d = ${direction.name}, r = $ratio")
        Log.d(TAG, manager.topPosition.toString())
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d(TAG, "onCardSwiped: d = $direction")
//        when (direction) {
//            Direction.Left -> {
//                dislikeHandler()
//            }
//            Direction.Right -> {
//                likeHandler()
//            }
//            Direction.Bottom ->{
//                skipHandler()
//            }
//            else -> {}
//        }
        Log.d(TAG, manager.topPosition.toString())
    }

    override fun onCardRewound() {
        Log.d(TAG, "onCardRewound")
        Log.d(TAG, manager.topPosition.toString())
    }

    override fun onCardCanceled() {
        Log.d(TAG, "onCardCanceled")
        Log.d(TAG, manager.topPosition.toString())
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d(TAG, "onCardAppeared: $position")
        currentPosition++
        Log.d(TAG, manager.topPosition.toString())
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d(TAG, "onCardDisappeared: $position")
        Log.d(TAG, manager.topPosition.toString())
    }

    private companion object {
        private const val TAG = "CompilationFragment"
    }
}