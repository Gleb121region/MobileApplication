package ru.spbstu.mobileapplication.data.network.auth.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("firstname") @Expose val firstname: String,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("password") @Expose val password: String,
    @SerializedName("role") @Expose val role: String
)
