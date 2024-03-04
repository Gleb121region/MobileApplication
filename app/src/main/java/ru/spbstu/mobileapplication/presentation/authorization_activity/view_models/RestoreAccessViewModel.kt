package ru.spbstu.mobileapplication.presentation.authorization_activity.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.spbstu.mobileapplication.domain.authentication.entity.RestoreItem
import ru.spbstu.mobileapplication.domain.authentication.usecase.RestoreAccessUseCase
import javax.inject.Inject

class RestoreAccessViewModel @Inject constructor(
    private val restoreAccessUseCase: RestoreAccessUseCase
) : ViewModel() {
    fun restoreAccess(
        email: String,
        newPassword: String,
        confirmationPassword: String
    ) = liveData {
        val restoreItem = RestoreItem(email, newPassword, confirmationPassword)
        val result = restoreAccessUseCase.restoreAccess(restoreItem)
        emit(result)
    }
}
