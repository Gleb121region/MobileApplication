package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.database.user.UserInfoDao
import ru.spbstu.mobileapplication.data.mapper.UserMapper
import ru.spbstu.mobileapplication.data.network.user.UserApiService
import ru.spbstu.mobileapplication.domain.user.entity.EditUserItem
import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApiService,
    private val mapper: UserMapper,
    private val userDao: UserInfoDao
) : UserRepository {

    override suspend fun getUserItemByToken(token: String): UserItem {
        return mapper.mapUserUpdateRequestToUserItem(api.getInfoAboutUser(token))
    }

    override suspend fun editUserItemByToken(token: String, editUserItem: EditUserItem) {
        api.updateUserInfo(token, mapper.mapEditUserItemToUserUpdateRequest(editUserItem))
    }

    override suspend fun deleteUserItemByToken(token: String) {
        api.deleteUser(token)
    }

    override suspend fun insetUserItem(userItem: UserItem) {
        return userDao.insertUser(mapper.mapUserItemToUserDBModel(userItem))
    }

    override suspend fun getUserItem(userItemId: Int): UserItem? {
        return userDao.findUserById(userItemId)?.let { mapper.mapUserDBModelToUserItem(it) }
    }

    override suspend fun editUserItem(userItem: UserItem) {
        userDao.updateUser(mapper.mapUserItemToUserDBModel(userItem))
    }

    override suspend fun deleteUserItem(userId: Int) {
        userDao.deleteUser(userId)
    }
}