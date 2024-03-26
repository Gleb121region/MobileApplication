package ru.spbstu.mobileapplication.data.network.feedback

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementResponse
import ru.spbstu.mobileapplication.data.network.feedback.model.request.CreateFeedbackRequest

interface FeedbackApiService {
    @Headers("accept: */*", "Content-Type: application/json")
    @PUT("/api/v1/feedback/assess")
    suspend fun createFeedback(
        @Body request: CreateFeedbackRequest,
        @Header("Authorization") token: String
    )

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/api/v1/feedback/liked")
    suspend fun getFeedbackLiked(@Header("Authorization") token: String): List<AnnouncementResponse>
}