package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.favorite

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import ru.spbstu.mobileapplication.domain.feedback.usecases.GetFeedbacksUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val createFeedbackUseCase: CreateFeedbackUseCase,
    private val getFeedbacksUseCase: GetFeedbacksUseCase
) : ViewModel() {

    suspend fun getFavoriteAnnouncements(
        limit: Int, offset: Int, token: String
    ): List<AnnouncementEntity> = getFeedbacksUseCase(limit, offset, token)

    suspend fun sendRequest(feedbackCreateEntity: FeedbackCreateEntity, token: String) {
        createFeedbackUseCase(feedbackCreateEntity, token)
    }
}