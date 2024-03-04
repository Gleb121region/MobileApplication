package ru.spbstu.mobileapplication.domain.authentication.repository

import ru.spbstu.mobileapplication.domain.authentication.entity.LoginItem
import ru.spbstu.mobileapplication.domain.authentication.entity.RefreshItem
import ru.spbstu.mobileapplication.domain.authentication.entity.RegisterItem
import ru.spbstu.mobileapplication.domain.authentication.entity.RestoreItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem

interface AuthRepository {
    suspend fun signUp(registerItem: RegisterItem): TokenItem
    suspend fun signIn(loginItem: LoginItem): TokenItem
    suspend fun restore(restoreItem: RestoreItem): TokenItem
//    suspend fun refresh(refreshItem: RefreshItem): TokenItem
}