package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementListUseCase
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.database.GetLastSurveyFromDataBaseUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase: GetAnnouncementListUseCase,
    private val createFeedbackUseCase: CreateFeedbackUseCase
) : ViewModel() {

    val announcements: MutableLiveData<MutableList<AnnouncementEntity>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        Log.d(TAG, "getLastSurveyFromDB started")
        isLoading.postValue(true)
        val model: AnswerDbModel = getLastSurveyFromDataBaseUseCase()
        isLoading.postValue(false)
        return model
    }

    suspend fun getAnnouncements(
        lastSurvey: AnswerDbModel, limit: Int = 10, offset: Int = 0, token: String
    ) {
        try {
            isLoading.postValue(true)
            val announcementEntities =
                getAnnouncementListUseCase(lastSurvey, limit, offset, token).toMutableList()
            if (offset == 0) {
                announcements.postValue(announcementEntities)
            } else {
                val currentList = announcements.value ?: mutableListOf()
                currentList.addAll(announcementEntities)
                announcements.postValue(currentList)
            }
            isLoading.postValue(false)
            Log.d(TAG, announcements.toString())
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
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
