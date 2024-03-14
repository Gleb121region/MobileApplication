package ru.spbstu.mobileapplication.presentation.authorization_activity.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ru.spbstu.mobileapplication.domain.authentication.entity.RegisterItem
import ru.spbstu.mobileapplication.domain.authentication.usecase.SignUpUseCase
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    fun signUp(firstName: String, email: String, password: String, role: String) = liveData {
        val registerItem = RegisterItem(firstName, email, password, role)
        val result = signUpUseCase.signUp(registerItem)
        emit(result)
    }
}
