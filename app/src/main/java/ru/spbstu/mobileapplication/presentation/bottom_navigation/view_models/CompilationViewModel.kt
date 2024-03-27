package ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementListUseCase
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.database.GetLastSurveyFromDataBaseUseCase
import javax.inject.Inject

class CompilationViewModel @Inject constructor(
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase: GetAnnouncementListUseCase,
    private val createFeedbackUseCase: CreateFeedbackUseCase,
) : ViewModel() {


    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        Log.d(TAG, "getLastSurveyFromDB")
        return getLastSurveyFromDataBaseUseCase()
    }

    suspend fun sendRequest(
        lastSurvey: AnswerDbModel, limit: Int = 10, offset: Int = 0, token: String
    ): List<AnnouncementEntity> {
        Log.d(TAG, "sendRequest announcement")
        return getAnnouncementListUseCase(lastSurvey, limit, offset, token)
    }

    suspend fun sendRequest(
        feedbackCreateEntity: FeedbackCreateEntity, token: String
    ) {
        Log.d(TAG, "sendRequest feedback")
        createFeedbackUseCase(feedbackCreateEntity, token)
    }

    companion object {
        private const val TAG = "CompilationViewModel"
    }
}