package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.modify

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.user.entity.EditUserItem
import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.domain.user.usecase.database.GetUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.database.UpdateUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.network.GetUserByTokenUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.network.UpdateUserByTokenUseCase
import javax.inject.Inject

class ModifyUserDataViewModel @Inject constructor(
    // Database
    private val getUserFromDatabaseUseCase: GetUserFromDatabaseUseCase,
    private val updateUserFromDatabaseUseCase: UpdateUserFromDatabaseUseCase,
    // Network
    private val getUserByTokenUseCase: GetUserByTokenUseCase,
    private val updateUserByTokenUseCase: UpdateUserByTokenUseCase,
) : ViewModel() {
    val firstname = ObservableField<String>()
    val lastname = ObservableField<String>()
    val email = ObservableField<String>()
    val phone = ObservableField<String>()
    val about = ObservableField<String>()

    suspend fun getUser(userId: Int): UserItem? = getUserFromDatabaseUseCase(userId)

    suspend fun updateUser(userItem: UserItem) {
        updateUserFromDatabaseUseCase(userItem)
    }

    suspend fun getUserByToken(token: String): UserItem = getUserByTokenUseCase(token)


    suspend fun updateUserByToken(token: String, editUserItem: EditUserItem) {
        updateUserByTokenUseCase(token, editUserItem)
    }
}
