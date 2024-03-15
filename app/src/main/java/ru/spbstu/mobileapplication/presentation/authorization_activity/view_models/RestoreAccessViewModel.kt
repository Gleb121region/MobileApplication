package ru.spbstu.mobileapplication.presentation.authorization_activity.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.domain.authentication.entity.RestoreItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.usecase.RestoreAccessUseCase
import javax.inject.Inject

class RestoreAccessViewModel @Inject constructor(
    private val restoreAccessUseCase: RestoreAccessUseCase
) : ViewModel() {

    sealed class RestoreAccessResult {
        data class Success(val tokenItem: TokenItem) : RestoreAccessResult()
        data class Error(val message: String) : RestoreAccessResult()
    }

    val restoreAccessResult: MutableLiveData<RestoreAccessResult> = MutableLiveData()

    fun restoreAccess(email: String, newPassword: String, confirmationPassword: String) {
        viewModelScope.launch {
            try {
                val restoreItem = RestoreItem(email, newPassword, confirmationPassword)
                val result = restoreAccessUseCase.restoreAccess(restoreItem)
                restoreAccessResult.postValue(RestoreAccessResult.Success(result))
            } catch (e: Exception) {
                restoreAccessResult.postValue(RestoreAccessResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
