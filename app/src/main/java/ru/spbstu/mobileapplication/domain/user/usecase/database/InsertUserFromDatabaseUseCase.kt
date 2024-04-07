package ru.spbstu.mobileapplication.domain.user.usecase.database

import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import ru.spbstu.mobileapplication.domain.user.repository.UserRepository
import javax.inject.Inject

class InsertUserFromDatabaseUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userItem: UserItem) =
        userRepository.insetUserItem(userItem)
}