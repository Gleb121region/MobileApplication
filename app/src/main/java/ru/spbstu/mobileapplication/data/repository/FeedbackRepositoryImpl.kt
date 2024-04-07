package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.mapper.AnnouncementMapper
import ru.spbstu.mobileapplication.data.mapper.FeedbackMapper
import ru.spbstu.mobileapplication.data.network.feedback.FeedbackApiService
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.repository.FeedbackRepository
import javax.inject.Inject

class FeedbackRepositoryImpl @Inject constructor(
    private val api: FeedbackApiService,
    private val feedbackMapper: FeedbackMapper,
    private val announcementMapper: AnnouncementMapper
) : FeedbackRepository {
    override suspend fun createFeedback(feedbackEntity: FeedbackCreateEntity, token: String) {
        val feedback = feedbackMapper.mapFeedbackCreateEntityToCreateFeedbackRequest(feedbackEntity)
        api.createFeedback(feedback, token)
    }

    override suspend fun getFeedbacks(
        limit: Int, offset: Int, token: String
    ): List<AnnouncementEntity> {
        val liked = api.getFeedbackLiked(limit, offset, token)
        return liked.map { announcementMapper.mapAnnouncementResponseToAnnouncementEntity(it) }
    }
}