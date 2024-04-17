package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.data.database.announcement.AnnouncementDbModel
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementListUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.database.GetAnnouncementByIdFromDataBaseUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.database.GetAnnouncementsFromDataBaseUseCase
import ru.spbstu.mobileapplication.domain.announcement.usecases.database.InsertAnnouncementIntoDataBaseUseCase
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.database.GetLastSurveyFromDataBaseUseCase
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.HomeViewModel
import javax.inject.Inject

class CompilationViewModel @Inject constructor(
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase: GetAnnouncementListUseCase,
    private val createFeedbackUseCase: CreateFeedbackUseCase,
    private val insertAnnouncementIntoDataBaseUseCase: InsertAnnouncementIntoDataBaseUseCase,
    private val getAnnouncementByIdFromDataBaseUseCase: GetAnnouncementByIdFromDataBaseUseCase,
    private val getAnnouncementsFromDataBaseUseCase: GetAnnouncementsFromDataBaseUseCase
) : ViewModel() {

    val announcements: MutableLiveData<MutableList<AnnouncementEntity>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    //DB
    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        Log.d(TAG, "getLastSurveyFromDB started")
        isLoading.postValue(true)
        val model: AnswerDbModel = getLastSurveyFromDataBaseUseCase()
        isLoading.postValue(false)
        return model
    }

    suspend fun insertAnnouncementIntoDB(announcement: AnnouncementEntity) {
        Log.d(TAG, "insert announcement into DB")
        return insertAnnouncementIntoDataBaseUseCase(announcement)
    }

    suspend fun announcementExistsInDB(id: Int): Boolean =
        getAnnouncementByIdFromDataBaseUseCase(id) != null


    suspend fun getAnnouncements(): List<AnnouncementDbModel> {
        Log.d(TAG, "getAnnouncements")
        return getAnnouncementsFromDataBaseUseCase()
    }

    // Net
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
        private const val TAG = "CompilationViewModel"
    }
}