package ru.spbstu.mobileapplication.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ru.spbstu.mobileapplication.data.repository.AnnouncementRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.AuthRepositoryImpl
import ru.spbstu.mobileapplication.data.repository.UserRepositoryImpl
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import ru.spbstu.mobileapplication.domain.authentication.repository.AuthRepository
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
    fun bindAnnouncementListRepository(
        impl: AnnouncementRepositoryImpl
    ): AnnouncementRepository

    @Singleton
    @Binds
    fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}