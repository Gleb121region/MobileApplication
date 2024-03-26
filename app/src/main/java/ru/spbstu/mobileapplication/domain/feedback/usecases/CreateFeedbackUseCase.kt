package ru.spbstu.mobileapplication.domain.feedback.usecases

import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.repository.FeedbackRepository
import javax.inject.Inject

class CreateFeedbackUseCase @Inject constructor(
    private val feedbackRepository: FeedbackRepository
) {
    suspend operator fun invoke(feedbackCreateEntity: FeedbackCreateEntity, token: String) {
        feedbackRepository.createFeedback(feedbackCreateEntity, token)
    }
}