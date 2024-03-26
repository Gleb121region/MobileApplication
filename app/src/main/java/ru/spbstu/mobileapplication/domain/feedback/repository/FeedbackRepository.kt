package ru.spbstu.mobileapplication.domain.feedback.repository

import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity

interface FeedbackRepository {
    suspend fun createFeedback(feedbackEntity: FeedbackCreateEntity, token: String)
    suspend fun getFeedbacks(token: String): List<AnnouncementEntity>
}