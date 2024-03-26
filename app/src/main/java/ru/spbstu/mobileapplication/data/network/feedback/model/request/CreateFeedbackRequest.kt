package ru.spbstu.mobileapplication.data.network.feedback.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.FeedbackType

data class CreateFeedbackRequest(
    @SerializedName("feedbackType") @Expose val feedbackType: FeedbackType,
    @SerializedName("announcementId") @Expose val announcementId: Int
) {
}