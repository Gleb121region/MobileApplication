package ru.spbstu.mobileapplication.data.network.auth

import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import ru.spbstu.mobileapplication.data.network.auth.model.request.AuthenticationRequest
import ru.spbstu.mobileapplication.data.network.auth.model.request.ChangePasswordRequest
import ru.spbstu.mobileapplication.data.network.auth.model.request.RegisterRequest
import ru.spbstu.mobileapplication.data.network.auth.model.response.AuthenticationResponse

interface AuthApiService {
    @POST("/api/v1/auth/register")
    suspend fun registerUser(@Body request: RegisterRequest): AuthenticationResponse

    @POST("/api/v1/auth/authenticate")
    suspend fun authenticateUser(@Body request: AuthenticationRequest): AuthenticationResponse

    @PATCH("/api/v1/auth/change-password")
    suspend fun changePassword(@Body request: ChangePasswordRequest): AuthenticationResponse
}