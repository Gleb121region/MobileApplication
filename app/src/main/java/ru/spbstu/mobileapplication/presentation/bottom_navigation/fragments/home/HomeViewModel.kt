package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

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
    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        return getLastSurveyFromDataBaseUseCase()
    }

    suspend fun getAnnouncements(
        lastSurvey: AnswerDbModel, limit: Int = 10, offset: Int = 0, token: String
    ): List<AnnouncementEntity> {
        return getAnnouncementListUseCase(lastSurvey, limit, offset, token)
    }

    suspend fun createFeedback(feedbackCreateEntity: FeedbackCreateEntity, token: String) {
        createFeedbackUseCase(feedbackCreateEntity, token)
    }
}
