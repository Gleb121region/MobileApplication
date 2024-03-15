package ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage

import ru.spbstu.mobileapplication.domain.authentication.repository.TokenRepository
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {

    operator fun invoke() {
        tokenRepository.deleteTokenFromLocalStorage()
    }

}
