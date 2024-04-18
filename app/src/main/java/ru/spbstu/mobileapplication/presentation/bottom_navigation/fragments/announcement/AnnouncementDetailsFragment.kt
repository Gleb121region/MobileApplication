package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.announcement

import android.content.Context
import android.content.Context.MODE_PRIVATE
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
import ru.spbstu.mobileapplication.databinding.FragmentAnnouncementDetailsBinding
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

    private var isLoading = false

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase

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


        viewModel.announcementDetails.observe(viewLifecycleOwner) { announcement ->
            binding.addressTextView.text = announcement.getFormattedAddress()
            binding.priceTextView.text = announcement.getFormattedPricePerMonth()
            binding.apartmentTypeTextView.text = announcement.getApartmentTypeRusName(context)
            binding.totalAreaTextView.text = announcement.getFormattedTotalMeters()
            binding.floorDetailsTextView.text = announcement.getFormattedFloorAndFloorsCount()
            binding.textviewMetroName.text = announcement.underground
            binding.photoNumberTextView.text = announcement.getFormattedPhotoPositionAndPhotosSize()

            Picasso.get().load(announcement.photoUrls[announcement.currentImagePosition])
                .into(binding.imageViewMainBackground)

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

            binding.likeImageView.setOnClickListener {
                val announcementId = getAnnouncementIdFromLocalStorage()
                val feedbackData = FeedbackCreateEntity(FeedbackType.LIKE, announcementId)
                Log.d(TAG, "likeImageView clicked")
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.submitFeedback(feedbackData, token)
                }
            }

            binding.backChooseIconButton.setOnClickListener {
                Log.d(TAG, "backChooseIconButton clicked")
                val tag = getTagFromLocalStorage()

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

        viewModel.featureList.observe(viewLifecycleOwner) { features ->
            Log.d(TAG, "featureList")
            featuresAdapter.features = features
            featuresAdapter.notifyDataSetChanged()
        }
    }


    private suspend fun loadAnnouncement() {
        if (isLoading) {
            Log.d(TAG, "Already loading, skip loading")
            return
        }
        isLoading = true

        val announcementId = getAnnouncementIdFromLocalStorage()

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

    private fun getAnnouncementIdFromLocalStorage(): Int {
        val sharedPreferences =
            requireContext().getSharedPreferences("announcement_prefs", MODE_PRIVATE)
        return sharedPreferences.getInt("selected_announcement_id", -1)
    }

    private fun getTagFromLocalStorage(): String? {
        val sharedPreferences =
            requireContext().getSharedPreferences("announcement_prefs", MODE_PRIVATE)
        return sharedPreferences.getString("was_worked_in", "null")
    }

    private companion object {
        private const val TAG = "AnnouncementDetailsFragment"
    }
}