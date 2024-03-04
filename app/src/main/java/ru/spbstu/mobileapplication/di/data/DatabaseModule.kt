package ru.spbstu.mobileapplication.di.data

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.spbstu.mobileapplication.data.database.AppDatabase
import ru.spbstu.mobileapplication.data.database.token.TokenInfoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideTokenInfoDao(
        application: Application
    ): TokenInfoDao {
        return AppDatabase.getInstance(application).TokenInfoDao()
    }
}
