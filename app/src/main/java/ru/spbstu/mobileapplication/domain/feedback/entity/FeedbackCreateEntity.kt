package ru.spbstu.mobileapplication.domain.feedback.entity

import ru.spbstu.mobileapplication.domain.enums.FeedbackType

data class FeedbackCreateEntity(
    val feedbackType: FeedbackType, val announcementId: Int
)
