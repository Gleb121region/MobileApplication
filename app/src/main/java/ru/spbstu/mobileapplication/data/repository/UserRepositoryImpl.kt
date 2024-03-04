package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
) : UserRepository {
    override suspend fun getUserItem(userItemId: Int): UserItem {
        TODO("Not yet implemented")
    }

    override suspend fun editUserItem(userItem: UserItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserItem(userItem: UserItem) {
        TODO("Not yet implemented")
    }
}