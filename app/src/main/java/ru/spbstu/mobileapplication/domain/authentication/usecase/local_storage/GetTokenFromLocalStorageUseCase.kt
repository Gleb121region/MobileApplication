package ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage

import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.repository.TokenRepository
import javax.inject.Inject

class GetTokenFromLocalStorageUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {

    operator fun invoke(): TokenItem = tokenRepository.getTokenFromLocalStorage()

}
