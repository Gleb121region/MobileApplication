package ru.spbstu.mobileapplication.domain.authentication.usecase

import ru.spbstu.mobileapplication.domain.authentication.entity.RestoreItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.repository.AuthRepository
import javax.inject.Inject

class RestoreAccessUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(restoreItem: RestoreItem): TokenItem {
        return authRepository.restore(restoreItem)
    }
}