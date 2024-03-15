package ru.spbstu.mobileapplication.domain.authentication.usecase

import android.util.Log
import ru.spbstu.mobileapplication.domain.authentication.entity.LoginItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginItem: LoginItem): TokenItem {
        Log.d(TAG, "Sign In")
        return authRepository.signIn(loginItem)
    }

    private companion object {
        private const val TAG = "SignInUseCase"
    }
}