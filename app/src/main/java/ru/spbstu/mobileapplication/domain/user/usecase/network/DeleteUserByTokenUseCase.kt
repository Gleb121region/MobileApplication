package ru.spbstu.mobileapplication.domain.user.usecase.network

import ru.spbstu.mobileapplication.domain.user.repository.UserRepository
import javax.inject.Inject

class DeleteUserByTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String) =
        userRepository.deleteUserItemByToken(token)
}