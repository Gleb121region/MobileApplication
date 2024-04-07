package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.cabinet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.domain.user.usecase.database.GetUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.database.InsertUserFromDatabaseUseCase
import ru.spbstu.mobileapplication.domain.user.usecase.network.GetUserByTokenUseCase
import javax.inject.Inject

class CabinetViewModel @Inject constructor(
    // Database
    private val insertUserFromDatabaseUseCase: InsertUserFromDatabaseUseCase,
    private val getUserFromDatabaseUseCase: GetUserFromDatabaseUseCase,
    // Network
    private val getUserByTokenUseCase: GetUserByTokenUseCase,
) : ViewModel() {

    val currentUser: MutableLiveData<UserItem> = MutableLiveData()

    suspend fun insertUser(userItem: UserItem) {
        insertUserFromDatabaseUseCase(userItem)
    }

    suspend fun getUser(userId: Int): UserItem? {
        return getUserFromDatabaseUseCase(userId)
    }

    suspend fun getUserByToken(token: String) {
        val user = getUserByTokenUseCase(token)
        currentUser.postValue(user)
    }

    companion object CabinetViewModel {
        const val TAG = "CabinetViewModel"
    }
}
