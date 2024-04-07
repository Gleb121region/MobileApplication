package ru.spbstu.mobileapplication.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.spbstu.mobileapplication.data.network.ApiFactory
import ru.spbstu.mobileapplication.data.network.announcement.AnnouncementApiService
import ru.spbstu.mobileapplication.data.network.auth.AuthApiService
import ru.spbstu.mobileapplication.data.network.feedback.FeedbackApiService
import ru.spbstu.mobileapplication.data.network.survey.SurveyApiService
import ru.spbstu.mobileapplication.data.network.user.UserApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun UserApiService(): UserApiService {
        return ApiFactory.userApiService
    }

    @Provides
    @Singleton
    fun AuthApiService(): AuthApiService {
        return ApiFactory.authApiService
    }

    @Provides
    @Singleton
    fun AnnouncementApiService(): AnnouncementApiService {
        return ApiFactory.announcementApiService
    }

    @Provides
    @Singleton
    fun FeedbackApiService(): FeedbackApiService {
        return ApiFactory.feedbackApiService
    }

    @Provides
    @Singleton
    fun SurveyApiService(): SurveyApiService {
        return ApiFactory.surveyApiService
    }
}