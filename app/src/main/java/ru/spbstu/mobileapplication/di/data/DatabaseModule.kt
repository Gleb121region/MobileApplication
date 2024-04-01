package ru.spbstu.mobileapplication.di.data

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.spbstu.mobileapplication.data.database.AppDatabase
import ru.spbstu.mobileapplication.data.database.announcement.AnnouncementInfoDao
import ru.spbstu.mobileapplication.data.database.answer.AnswerInfoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideSurveyAnswersInfoDao(
        application: Application
    ): AnswerInfoDao {
        return AppDatabase.getInstance(application).AnswerInfoDao()
    }

    @Provides
    @Singleton
    fun provideAnnouncementInfoDao(
        application: Application
    ): AnnouncementInfoDao {
        return AppDatabase.getInstance(application).AnnouncementInfoDao()
    }
}
