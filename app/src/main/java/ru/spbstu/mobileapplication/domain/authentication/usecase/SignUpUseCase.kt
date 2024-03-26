package ru.spbstu.mobileapplication.domain.authentication.usecase

import ru.spbstu.mobileapplication.domain.authentication.entity.RegisterItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(registerItem: RegisterItem): TokenItem {
        return authRepository.signUp(registerItem)
    }
}