package ru.spbstu.mobileapplication.domain.user.usecase.database

import ru.spbstu.mobileapplication.domain.user.repository.UserRepository
import javax.inject.Inject

class DeleteUserFromDatabaseUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Int) =
        userRepository.deleteUserItem(userId)
}