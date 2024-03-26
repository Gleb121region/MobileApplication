package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.mapper.AuthenticationMapper
import ru.spbstu.mobileapplication.data.network.auth.AuthApiService
import ru.spbstu.mobileapplication.domain.authentication.entity.LoginItem
import ru.spbstu.mobileapplication.domain.authentication.entity.RegisterItem
import ru.spbstu.mobileapplication.domain.authentication.entity.RestoreItem
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApiService,
    private val mapper: AuthenticationMapper,
) : AuthRepository {

    override suspend fun signUp(registerItem: RegisterItem): TokenItem {
        val requestDTO = mapper.mapRegisterItemToRegisterRequest(registerItem)
        val responseDTO = api.registerUser(requestDTO)
        return mapper.mapAuthenticationResponseToTokenItem(responseDTO)
    }

    override suspend fun signIn(loginItem: LoginItem): TokenItem {
        val requestDTO = mapper.mapLoginItemToAuthenticationRequest(loginItem)
        val responseDTO = api.authenticateUser(requestDTO)
        return mapper.mapAuthenticationResponseToTokenItem(responseDTO)
    }

    override suspend fun restore(restoreItem: RestoreItem): TokenItem {
        val requestDTO = mapper.mapRestoreItemToChangePasswordRequest(restoreItem)
        val responseDTO = api.changePassword(requestDTO)
        return mapper.mapAuthenticationResponseToTokenItem(responseDTO)
    }


//    override suspend fun refresh(refreshItem: RefreshItem): TokenItem {
//        TODO("Not yet implemented")
//    }

    private companion object {
        private const val TAG = "AuthRepositoryImpl"
    }
}