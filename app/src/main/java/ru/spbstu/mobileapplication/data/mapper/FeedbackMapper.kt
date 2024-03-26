package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.network.feedback.model.request.CreateFeedbackRequest
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import javax.inject.Inject

class FeedbackMapper @Inject constructor() {
    fun mapFeedbackCreateEntityToCreateFeedbackRequest(feedbackCreateEntity: FeedbackCreateEntity): CreateFeedbackRequest {
        return CreateFeedbackRequest(
            feedbackCreateEntity.feedbackType, feedbackCreateEntity.announcementId
        )

    }
}