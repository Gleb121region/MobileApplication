package ru.spbstu.mobileapplication.domain.feedback.usecases

import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.feedback.repository.FeedbackRepository
import javax.inject.Inject

class GetFeedbacksUseCase @Inject constructor(
    private val feedbackRepository: FeedbackRepository
) {
    suspend operator fun invoke(token: String): List<AnnouncementEntity> {
        return feedbackRepository.getFeedbacks(token)
    }

}