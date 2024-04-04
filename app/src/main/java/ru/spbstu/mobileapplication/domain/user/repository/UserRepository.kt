package ru.spbstu.mobileapplication.domain.user.repository

import ru.spbstu.mobileapplication.domain.user.entity.EditUserItem
import ru.spbstu.mobileapplication.domain.user.entity.UserItem

interface UserRepository {
    // NetWork
    suspend fun getUserItemByToken(token: String): UserItem
    suspend fun editUserItemByToken(token: String, editUserItem: EditUserItem)
    suspend fun deleteUserItemByToken(token: String)

    // Database
    suspend fun insetUserItem(userItem: UserItem)
    suspend fun getUserItem(userItemId: Int): UserItem?
    suspend fun editUserItem(userItem: UserItem)
    suspend fun deleteUserItem(userId: Int)
}
