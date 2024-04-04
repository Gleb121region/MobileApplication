package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.cabinet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.user.entity.EditUserItem
import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.domain.user.usecase.database.DeleteUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.database.GetUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.database.InsertUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.database.UpdateUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.network.DeleteUserByTokenUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.network.GetUserByTokenUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.network.UpdateUserByTokenUseCase
import javax.inject.Inject

class CabinetViewModel @Inject constructor(
    private val insertUserFromDatabaseUseCase: InsertUserFromDatabaseUseCase,
    private val deleteUserFromDatabaseUseCase: DeleteUserFromDatabaseUseCase,
    private val getUserFromDatabaseUseCase: GetUserFromDatabaseUseCase,
    private val updateUserFromDatabaseUseCase: UpdateUserFromDatabaseUseCase,

    private val deleteUserByTokenUseCase: DeleteUserByTokenUseCase,
    private val getUserByTokenUseCase: GetUserByTokenUseCase,
    private val updateUserByTokenUseCase: UpdateUserByTokenUseCase
) : ViewModel() {

    val currentUser: MutableLiveData<UserItem> = MutableLiveData()

    suspend fun insertUser(userItem: UserItem) {
        insertUserFromDatabaseUseCase(userItem)
    }

    suspend fun deleteUser(userId: Int) {
        deleteUserFromDatabaseUseCase(userId)
    }

    suspend fun getUser(userId: Int): UserItem? {
        return getUserFromDatabaseUseCase(userId)
    }

    suspend fun updateUser(userItem: UserItem) {
        updateUserFromDatabaseUseCase(userItem)
    }

    suspend fun deleteUserByToken(token: String) {
        deleteUserByTokenUseCase(token)
    }

    suspend fun getUserByToken(token: String) {
        val user = getUserByTokenUseCase(token)
        currentUser.postValue(user)
    }

    suspend fun updateUserByToken(token: String, editUserItem: EditUserItem) {
        updateUserByTokenUseCase(token, editUserItem)
    }

    companion object CabinetViewModel {
        const val TAG = "CabinetViewModel"
    }
}
