package ru.spbstu.mobileapplication.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.spbstu.mobileapplication.data.network.announcement.AnnouncementApiService
import ru.spbstu.mobileapplication.data.network.auth.AuthApiService
import ru.spbstu.mobileapplication.data.network.feedback.FeedbackApiService
import ru.spbstu.mobileapplication.data.network.survey.SurveyApiService
import ru.spbstu.mobileapplication.data.network.user.UserApiService

object ApiFactory {

    private const val BASE_URL: String = "http://10.0.2.2:8080" // My server URL

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val authApiService: AuthApiService = retrofit.create(AuthApiService::class.java)
    val announcementApiService: AnnouncementApiService =
        retrofit.create(AnnouncementApiService::class.java)
    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)
    val surveyApiService: SurveyApiService = retrofit.create(SurveyApiService::class.java)
    val feedbackApiService: FeedbackApiService = retrofit.create(FeedbackApiService::class.java)
}