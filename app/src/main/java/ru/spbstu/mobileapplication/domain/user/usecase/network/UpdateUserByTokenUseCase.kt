package ru.spbstu.mobileapplication.domain.user.usecase.network

import ru.spbstu.mobileapplication.domain.user.entity.EditUserItem
import ru.spbstu.mobileapplication.domain.user.repository.UserRepository
import javax.inject.Inject

class UpdateUserByTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String, editUserItem: EditUserItem) =
        userRepository.editUserItemByToken(token, editUserItem)
}