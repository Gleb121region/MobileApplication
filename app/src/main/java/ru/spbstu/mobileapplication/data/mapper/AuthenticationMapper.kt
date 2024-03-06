package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.network.auth.model.request.AuthenticationRequest
import ru.spbstu.mobileapplication.data.network.auth.model.request.ChangePasswordRequest
import ru.spbstu.mobileapplication.data.network.auth.model.request.RegisterRequest
import ru.spbstu.mobileapplication.data.network.auth.model.response.AuthenticationResponse
import ru.spbstu.mobileapplication.domain.authentication.entity.LoginItem
import ru.spbstu.mobileapplication.domain.authentication.entity.RegisterItem
import ru.spbstu.mobileapplication.domain.authentication.entity.RestoreItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import javax.inject.Inject

class AuthenticationMapper @Inject constructor() {
     fun mapRegisterItemToRegisterRequest(item: RegisterItem): RegisterRequest =
        RegisterRequest(
            firstname = item.firstName,
            email = item.email,
            password = item.password,
            role = item.role
        )


    fun mapAuthenticationResponseToTokenItem(response: AuthenticationResponse): TokenItem =
        TokenItem(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken
        )


    fun mapLoginItemToAuthenticationRequest(item: LoginItem): AuthenticationRequest =
        AuthenticationRequest(
            email = item.email,
            password = item.password
        )


    fun mapRestoreItemToChangePasswordRequest(item: RestoreItem): ChangePasswordRequest =
        ChangePasswordRequest(
            email = item.email,
            newPassword = item.newPassword,
            confirmationPassword = item.confirmationPassword
        )
}