package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.favorite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import ru.spbstu.mobileapplication.domain.feedback.usecases.GetFeedbacksUseCase
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.HomeViewModel
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val createFeedbackUseCase: CreateFeedbackUseCase,
    private val getFeedbacksUseCase: GetFeedbacksUseCase
) : ViewModel() {

    val selectedAnnouncementId: MutableLiveData<Int> = MutableLiveData()
    val announcements: MutableLiveData<MutableList<AnnouncementEntity>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun getFavoriteAnnouncements(limit: Int, offset: Int, token: String) {
        try {
            isLoading.postValue(true)
            val announcementEntities = getFeedbacksUseCase(limit, offset, token).toMutableList()
            if (offset == 0) {
                announcements.postValue(announcementEntities)
            } else {
                val currentList = announcements.value ?: mutableListOf()
                currentList.addAll(announcementEntities)
                announcements.postValue(currentList)
            }
            isLoading.postValue(false)
            Log.d(HomeViewModel.TAG, announcements.toString())
        } catch (e: Exception) {
            Log.d(HomeViewModel.TAG, e.toString())
        }
    }

    suspend fun createFeedback(feedbackCreateEntity: FeedbackCreateEntity, token: String) {
        Log.d(TAG, "createFeedback started")
        isLoading.postValue(true)
        createFeedbackUseCase(feedbackCreateEntity, token)
        isLoading.postValue(false)
        Log.d(TAG, "createFeedback finished")
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}