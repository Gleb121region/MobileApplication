package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.storage.auth.SharedPreferencesStorage
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : TokenRepository {

    override fun saveTokenToLocalStorage(token: TokenItem) {
        sharedPreferencesStorage.saveToken(token)
    }

    override fun getTokenFromLocalStorage(): TokenItem {
        return sharedPreferencesStorage.getToken()
    }

    override fun deleteTokenFromLocalStorage() {
        sharedPreferencesStorage.deleteToken()
    }
}