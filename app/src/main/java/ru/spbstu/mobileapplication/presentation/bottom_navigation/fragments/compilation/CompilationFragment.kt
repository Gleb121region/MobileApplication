package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod
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
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class CompilationFragment : Fragment(), CardStackListener {
    private val manager by lazy { CardStackLayoutManager(this.context, this) }

    private lateinit var viewModel: CompilationViewModel
    private lateinit var adapter: CardStackViewAdapter
    private lateinit var token: String
    private lateinit var cardStackView: CardStackView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    private var currentPosition: Int = 0
    private var isClicked: Boolean = false

    private var currentOffset: Int = 0

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
            token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            val model = viewModel.getLastSurveyFromDB()
            try {
                loadAnnouncements(model, 10, 0)
                setupCardStackView()
            } catch (e: Exception) {
                Log.e(TAG, "Error in network", e)
            }
        }
        cardStackView = binding.cardStackView
        Log.d(TAG, "CompilationFragment onViewCreated")
    }

    private suspend fun loadAnnouncements(lastSurvey: AnswerDbModel, limit: Int, offset: Int) {
        val announcements =
            viewModel.getAnnouncements(lastSurvey, limit, offset, token).toMutableList()
        if (announcements.isEmpty()) {
            Log.d(TAG, "No more announcements to load")
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            for (announcement in announcements) {
                viewModel.insertAnnouncementIntoDB(announcement)
            }
        }

        adapter = CardStackViewAdapter(announcements)
        cardStackView.adapter = adapter

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.dislikeButton.setOnClickListener {
            handleFeedback(FeedbackType.DISLIKE)
        }

        binding.skipButton.setOnClickListener {
            handleFeedback(FeedbackType.SKIP)
        }
        binding.likeButton.setOnClickListener {
            handleFeedback(FeedbackType.LIKE)
        }
    }

    private fun handleFeedback(feedbackType: FeedbackType) {
        Log.d(TAG, "${feedbackType.name.lowercase(Locale.ROOT)} clicked")
        isClicked = true
        Log.d(TAG, "value variable isClicked is $isClicked")
        val announcement = adapter.announcements[currentPosition]
        val feedbackCreateEntity = FeedbackCreateEntity(feedbackType, announcement.id)
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.createFeedback(feedbackCreateEntity, token)
        }

        val direction: Direction
        val interpolator: Interpolator
        when (feedbackType) {
            FeedbackType.LIKE -> {
                direction = Direction.Right
                interpolator = AccelerateInterpolator()
            }

            FeedbackType.SKIP -> {
                direction = Direction.Bottom
                interpolator = DecelerateInterpolator()
            }

            FeedbackType.DISLIKE -> {
                direction = Direction.Left
                interpolator = AccelerateInterpolator()
            }
        }

        val setting = SwipeAnimationSetting.Builder().setDirection(direction)
            .setDuration(Duration.Normal.duration).setInterpolator(interpolator).build()
        manager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d(TAG, "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d(TAG, "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (isClicked) {
            isClicked = false
            return
        }
        when (direction) {
            Direction.Left -> {
                handleFeedback(FeedbackType.DISLIKE)
            }

            Direction.Right -> {
                handleFeedback(FeedbackType.LIKE)
            }
            // todo: разобраться не работает пока что!
            Direction.Bottom -> {
                handleFeedback(FeedbackType.SKIP)
            }

            else -> {}
        }
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
        if (position == adapter.itemCount - 1) {
            Log.d(TAG, "Need more ads")
        }
        Log.d(TAG, manager.topPosition.toString())
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d(TAG, "onCardDisappeared: $position")
        Log.d(TAG, manager.topPosition.toString())
    }

    private fun setupCardStackView() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        cardStackView.layoutManager = manager

        cardStackView.adapter = adapter

        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private companion object {
        private const val TAG = "CompilationFragment"
    }
}