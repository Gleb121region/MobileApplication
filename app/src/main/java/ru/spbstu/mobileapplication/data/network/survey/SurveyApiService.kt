package ru.spbstu.mobileapplication.data.network.survey

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.spbstu.mobileapplication.data.network.survey.model.request.CreateSurveyRequest
import ru.spbstu.mobileapplication.data.network.survey.model.response.GetSurveyResponse

interface SurveyApiService {
    @Headers("accept: */*")
    @GET("/api/v1/survey")
    suspend fun getSurvey(@Header("Authorization") token: String): Set<GetSurveyResponse>

    @Headers("accept: */*")
    @POST("/api/v1/survey/create")
    suspend fun createSurvey(
        @Header("Authorization") token: String, @Body request: CreateSurveyRequest
    )
}