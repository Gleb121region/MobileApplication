package ru.spbstu.mobileapplication.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.spbstu.mobileapplication.data.repository.AnnouncementRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.AnnouncementStorageRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.AuthRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.FeedbackRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.SurveyAnswersRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.TokenRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.UserRepositoryImpl
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementStorageRepository
import ru.spbstu.mobileapplication.domain.authentication.repository.AuthRepository
import ru.spbstu.mobileapplication.domain.authentication.repository.TokenRepository
import ru.spbstu.mobileapplication.domain.feedback.repository.FeedbackRepository
import ru.spbstu.mobileapplication.domain.survey.repository.SurveyAnswersRepository
import ru.spbstu.mobileapplication.domain.user.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    fun bindTokenRepository(
        impl: TokenRepositoryImpl
    ): TokenRepository

    @Singleton
    @Binds
    fun bindAnnouncementListRepository(
        impl: AnnouncementRepositoryImpl
    ): AnnouncementRepository

    @Singleton
    @Binds
    fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Singleton
    @Binds
    fun bindSurveyAnswersRepository(
        impl: SurveyAnswersRepositoryImpl
    ): SurveyAnswersRepository

    @Singleton
    @Binds
    fun bindFeedbackRepository(
        impl: FeedbackRepositoryImpl
    ): FeedbackRepository

    @Singleton
    @Binds
    fun  bindAnnouncementStorageRepository(
        impl: AnnouncementStorageRepositoryImpl
    ): AnnouncementStorageRepository
}