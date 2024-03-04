package ru.spbstu.mobileapplication.data.network.auth.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("access_token") @Expose val accessToken: String,
    @SerializedName("refresh_token") @Expose val refreshToken: String
)
