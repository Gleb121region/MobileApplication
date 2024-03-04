package ru.spbstu.mobileapplication.presentation.authorization_activity.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.domain.authentication.entity.LoginItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.usecase.SignInUseCase
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    sealed class SignInResult {
        data class Success(val tokenItem: TokenItem) : SignInResult()
        data class Error(val message: String) : SignInResult()
    }

    val signInResult: MutableLiveData<SignInResult> = MutableLiveData()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val loginItem = LoginItem(email, password)
                val result = signInUseCase.signIn(loginItem)
                signInResult.postValue(SignInResult.Success(result))
            } catch (e: Exception) {
                signInResult.postValue(SignInResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}

