package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.compilation

import android.util.Log
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
import javax.inject.Inject

class CompilationViewModel @Inject constructor(
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase: GetAnnouncementListUseCase,
    private val createFeedbackUseCase: CreateFeedbackUseCase,
    private val insertAnnouncementIntoDataBaseUseCase: InsertAnnouncementIntoDataBaseUseCase,
    private val getAnnouncementByIdFromDataBaseUseCase: GetAnnouncementByIdFromDataBaseUseCase,
    private val getAnnouncementsFromDataBaseUseCase: GetAnnouncementsFromDataBaseUseCase
) : ViewModel() {

    //DB
    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        Log.d(TAG, "getLastSurveyFromDB")
        return getLastSurveyFromDataBaseUseCase()
    }

    suspend fun insertAnnouncementIntoDB(announcement: AnnouncementEntity) {
        Log.d(TAG, "recordIntoDB")
        return insertAnnouncementIntoDataBaseUseCase(announcement)
    }

    suspend fun getAnnouncementById(id: Int): AnnouncementDbModel? {
        Log.d(TAG, "getAnnouncementById")
        return getAnnouncementByIdFromDataBaseUseCase(id)
    }

    suspend fun getAnnouncements(): List<AnnouncementDbModel> {
        Log.d(TAG, "getAnnouncements")
        return getAnnouncementsFromDataBaseUseCase()
    }

    // Net
    suspend fun getAnnouncements(
        lastSurvey: AnswerDbModel, limit: Int = 10, offset: Int = 0, token: String
    ): List<AnnouncementEntity> {
        Log.d(TAG, "sendRequest announcement")
        return getAnnouncementListUseCase(lastSurvey, limit, offset, token)
    }

    suspend fun createFeedback(
        feedbackCreateEntity: FeedbackCreateEntity, token: String
    ) {
        Log.d(TAG, "sendRequest feedback")
        createFeedbackUseCase(feedbackCreateEntity, token)
    }

    companion object {
        private const val TAG = "CompilationViewModel"
    }
}