package ru.spbstu.mobileapplication.data.network.auth.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("email") @Expose val email: String,
    @SerializedName("newPassword") @Expose val newPassword: String,
    @SerializedName("confirmationPassword") @Expose val confirmationPassword: String,
)
