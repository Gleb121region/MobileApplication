package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.announcement

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.databinding.FragmentAnnouncementDetailsBinding
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementDetailedEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.GetAnnouncementIdUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage.GetTagUseCase
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.enums.FeedbackType
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.presentation.App
import ru.spbstu.mobileapplication.presentation.ViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class AnnouncementDetailsFragment : Fragment() {

    private lateinit var viewModel: AnnouncementDetailsViewModel

    private lateinit var token: String

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

    @Inject
    lateinit var getAnnouncementIdUseCase: GetAnnouncementIdUseCase

    @Inject
    lateinit var getTagUseCase: GetTagUseCase

    private var _binding: FragmentAnnouncementDetailsBinding? = null
    private val binding: FragmentAnnouncementDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).component.inject(this)
        Log.d(TAG, "AnnouncementDetailsFragment onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnnouncementDetailsBinding.inflate(inflater, container, false)
        Log.d(TAG, "AnnouncementDetailsFragment onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[AnnouncementDetailsViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            loadAnnouncement()
        }

        val featuresAdapter = FeaturesAdapter(listOf())
        val context = requireContext()
        binding.advertised.layoutManager = LinearLayoutManager(context)
        binding.advertised.adapter = featuresAdapter

        observeAnnouncementDetails(context)
        observeFeatureList(featuresAdapter)
    }

    private fun observeAnnouncementDetails(context: Context) {
        viewModel.announcementDetails.observe(viewLifecycleOwner) { announcement ->
            binding.addressTextView.text = announcement.getFormattedAddress()
            binding.priceTextView.text = announcement.getFormattedPricePerMonth()
            binding.apartmentTypeTextView.text = announcement.getApartmentTypeRusName(context)
            binding.totalAreaTextView.text = announcement.getFormattedTotalMeters()
            binding.floorDetailsTextView.text = announcement.getFormattedFloorAndFloorsCount()
            binding.textviewMetroName.text = announcement.underground
            binding.photoNumberTextView.text = announcement.getFormattedPhotoPositionAndPhotosSize()
            binding.tvDescription.text = announcement.description

            Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
                .into(binding.imageViewMainBackground)

            setupClickListeners(announcement)

            observeIsLike()
        }
    }

    private fun setupClickListeners(announcement: AnnouncementDetailedEntity) {
        setupClickListenerPreviousLayout(announcement)
        setupClickListenerNextLayout(announcement)
        setupClickListenerLikeImageView()
        setupClickListenerBackIconButton()
    }

    private fun setupClickListenerBackIconButton() {
        binding.backChooseIconButton.setOnClickListener {
            Log.d(TAG, "backChooseIconButton clicked")
            val tag = getTagUseCase()

            if (tag.equals("HomeFragment")) {
                navigateToHome()
            }
            if (tag.equals("CompilationFragment")) {
                navigateToCompilation()
            }
            if (tag.equals("FavoriteFragment")) {
                navigateToFavorite()
            }
        }
    }

    private fun setupClickListenerLikeImageView() {
        binding.likeImageView.setOnClickListener {
            val announcementId = getAnnouncementIdUseCase()

            Log.d(TAG, "likeImageView clicked")
            lifecycleScope.launch(Dispatchers.IO) {
                val feedbackData = if (viewModel.isLiked.value == false) {
                    FeedbackCreateEntity(FeedbackType.LIKE, announcementId)
                } else {
                    FeedbackCreateEntity(FeedbackType.DEFAULT, announcementId)
                }
                viewModel.submitFeedback(feedbackData, token)
            }
        }
    }

    private fun setupClickListenerNextLayout(announcement: AnnouncementDetailedEntity) {
        binding.nextLayout.setOnClickListener {
            Log.d(TAG, "nextLayout clicked")
            if (announcement.currentImagePosition < announcement.photoUrls.size - 1) {
                announcement.currentImagePosition++
                binding.photoNumberTextView.text =
                    announcement.getFormattedPhotoPositionAndPhotosSize()
                Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
                    .into(binding.imageViewMainBackground)
            }
        }
    }

    private fun setupClickListenerPreviousLayout(announcement: AnnouncementDetailedEntity) {
        binding.previousLayout.setOnClickListener {
            Log.d(TAG, "previousLayout clicked")
            if (announcement.currentImagePosition > 0) {
                announcement.currentImagePosition--
                binding.photoNumberTextView.text =
                    announcement.getFormattedPhotoPositionAndPhotosSize()
                Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
                    .into(binding.imageViewMainBackground)
            }
        }
    }

    private fun observeIsLike() {
        viewModel.isLiked.observe(viewLifecycleOwner) { isLiked ->
            if (isLiked) {
                binding.likeImageView.setImageResource(R.drawable.like_green_24dp)
            } else {
                binding.likeImageView.setImageResource(R.drawable.like_gray_24dp)
            }
        }
    }

    private fun observeFeatureList(featuresAdapter: FeaturesAdapter) {
        viewModel.featureList.observe(viewLifecycleOwner) { features ->
            Log.d(TAG, "featureList")
            featuresAdapter.features = features
            featuresAdapter.notifyDataSetChanged()
        }
    }


    private suspend fun loadAnnouncement() {
        val announcementId = getAnnouncementIdUseCase()
        viewModel.fetchAnnouncementDetails(announcementId, token)
    }


    private fun navigateToHome() {
        findNavController().navigate(
            AnnouncementDetailsFragmentDirections.actionAnnouncementDetailsFragmentToNavigationHome()
        )
    }

    private fun navigateToCompilation() {
        findNavController().navigate(
            AnnouncementDetailsFragmentDirections.actionAnnouncementDetailsFragmentToNavigationCompilation()
        )
    }

    private fun navigateToFavorite() {
        findNavController().navigate(
            AnnouncementDetailsFragmentDirections.actionAnnouncementDetailsFragmentToNavigationFavorite()
        )
    }

    private companion object {
        private const val TAG = "AnnouncementDetailsFragment"
    }
}