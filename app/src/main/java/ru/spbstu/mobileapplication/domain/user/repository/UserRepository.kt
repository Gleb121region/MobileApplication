package ru.spbstu.mobileapplication.domain.user.repository

import ru.spbstu.mobileapplication.domain.user.entity.UserItem

interface UserRepository {
    suspend fun getUserItem(userItemId: Int): UserItem
    suspend fun editUserItem(userItem: UserItem)
    suspend fun deleteUserItem(userItem: UserItem)
}
