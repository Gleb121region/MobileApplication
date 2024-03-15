package ru.spbstu.mobileapplication.data.storage

import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem

interface TokenStorage {
    fun saveToken(tokenItem: TokenItem)
    fun getToken(): TokenItem
    fun deleteToken()

    companion object {
        const val TOKEN_KEY = "userToken"
        const val REFRESH_TOKEN_KEY = "refreshUserToken"
    }
}