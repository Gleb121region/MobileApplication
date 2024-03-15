package ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage

import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.repository.TokenRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {

    operator fun invoke(tokenItem: TokenItem) {
        return tokenRepository.saveTokenToLocalStorage(tokenItem)
    }

}
