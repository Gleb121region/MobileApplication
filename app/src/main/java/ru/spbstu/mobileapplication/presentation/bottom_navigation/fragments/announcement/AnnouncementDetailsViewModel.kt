package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.announcement

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementDetailedEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementItemUseCase
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import javax.inject.Inject

class AnnouncementDetailsViewModel @Inject constructor(
    private val fetchAnnouncementUseCase: GetAnnouncementItemUseCase,
    private val submitFeedbackUseCase: CreateFeedbackUseCase
) : ViewModel() {

    val announcementDetails: MutableLiveData<AnnouncementDetailedEntity> = MutableLiveData()
    val featureList: MutableLiveData<List<String>> = MutableLiveData()

    val isLiked: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDataLoading: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun fetchAnnouncementDetails(announcementId: Int, authToken: String) {
        Log.d(TAG, "Fetching announcement details started")
        isDataLoading.postValue(true)
        val fetchedAnnouncement = fetchAnnouncementUseCase(announcementId, authToken)
        announcementDetails.postValue(fetchedAnnouncement)
        val fetchedFeatures = fetchedAnnouncement.getFeatures()
        featureList.postValue(fetchedFeatures)
        isDataLoading.postValue(false)
        Log.d(TAG, "Fetching announcement details finished")
    }

    suspend fun submitFeedback(feedbackData: FeedbackCreateEntity, authToken: String) {
        Log.d(TAG, "Submitting feedback started")
        isDataLoading.postValue(true)
        submitFeedbackUseCase(feedbackData, authToken)
        isLiked.postValue(true)
        isDataLoading.postValue(false)
        Log.d(TAG, "Submitting feedback finished")
    }

    companion object {
        const val TAG = "AnnouncementDetailsViewModel"
    }
}
