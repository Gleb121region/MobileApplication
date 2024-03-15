package ru.spbstu.mobileapplication.presentation.authorization_activity.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.domain.authentication.entity.RegisterItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.usecase.SignUpUseCase
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.SaveTokenUseCase
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    sealed class SignUpResult {
        data class Success(val tokenItem: TokenItem) : SignUpResult()
        data class Error(val message: String) : SignUpResult()
    }

    val signUpResult: MutableLiveData<SignUpResult> = MutableLiveData()

    fun signUp(firstName: String, email: String, password: String, role: String) {
        viewModelScope.launch {
            try {
                val registerItem = RegisterItem(firstName, email, password, role)
                val result = signUpUseCase(registerItem)
                saveTokenUseCase(result)
                signUpResult.postValue(SignUpResult.Success(result))
            } catch (e: Exception) {
                signUpResult.postValue(SignUpResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}

