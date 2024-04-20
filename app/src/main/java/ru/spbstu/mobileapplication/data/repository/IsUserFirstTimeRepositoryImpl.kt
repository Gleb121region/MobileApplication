package ru.spbstu.mobileapplication.data.repository

import android.content.Context
import ru.spbstu.mobileapplication.data.storage.auth.SharedPreferencesStorage
import ru.spbstu.mobileapplication.domain.user.repository.IsUserFirstTimeRepository

class IsUserFirstTimeRepositoryImpl(context: Context) : IsUserFirstTimeRepository {
    private val sharedPreferencesStorage by lazy {
        SharedPreferencesStorage(context)
    }

    override fun changeFirstTimeStatus() {
        sharedPreferencesStorage.changeFirstTimeStatus()
    }

    override fun getFirstTimeStatus(): Boolean {
        return sharedPreferencesStorage.getFirstTimeStatus()
    }
}