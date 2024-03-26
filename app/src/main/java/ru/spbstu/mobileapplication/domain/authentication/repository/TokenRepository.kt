package ru.spbstu.mobileapplication.domain.authentication.repository

import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem


interface TokenRepository {
    fun saveTokenToLocalStorage(token: TokenItem)

    fun getTokenFromLocalStorage(): TokenItem

    fun deleteTokenFromLocalStorage()
}