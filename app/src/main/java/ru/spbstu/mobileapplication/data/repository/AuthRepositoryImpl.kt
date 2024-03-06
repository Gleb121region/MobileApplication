package ru.spbstu.mobileapplication.data.repository

import android.util.Log
import ru.spbstu.mobileapplication.data.database.token.TokenDbModel
import ru.spbstu.mobileapplication.data.database.token.TokenInfoDao
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
    private val tokenDao: TokenInfoDao
) : AuthRepository {

    override suspend fun signUp(registerItem: RegisterItem): TokenItem {
        val requestDTO = mapper.mapRegisterItemToRegisterRequest(registerItem)
        val responseDTO = api.registerUser(requestDTO)
        val tokenItem = mapper.mapAuthenticationResponseToTokenItem(responseDTO)
        insertIntoTableToken(tokenItem)
        return tokenItem
    }

    override suspend fun signIn(loginItem: LoginItem): TokenItem {
        Log.d(TAG, "signIn")
        val requestDTO = mapper.mapLoginItemToAuthenticationRequest(loginItem)
        Log.d(TAG, "mapper")
        val responseDTO = api.authenticateUser(requestDTO)
        Log.d(TAG, "network")
        val tokenItem = mapper.mapAuthenticationResponseToTokenItem(responseDTO)
        Log.d(TAG, "mapper")
        saveOrUpdateIncludeTableToken(tokenItem)
        return tokenItem
    }

    override suspend fun restore(restoreItem: RestoreItem): TokenItem {
        val requestDTO = mapper.mapRestoreItemToChangePasswordRequest(restoreItem)
        val responseDTO = api.changePassword(requestDTO)
        val tokenItem = mapper.mapAuthenticationResponseToTokenItem(responseDTO)
        saveOrUpdateIncludeTableToken(tokenItem)
        return tokenItem
    }

    private suspend fun insertIntoTableToken(tokenItem: TokenItem) {
        tokenDao.insertToken(
            TokenDbModel(token = tokenItem.accessToken, refreshToken = tokenItem.refreshToken)
        )
    }

    private suspend fun updateIntoTableToken(existToken: TokenDbModel, tokenItem: TokenItem) {
        tokenDao.updateToken(
            TokenDbModel(
                tokenId = existToken.tokenId,
                token = tokenItem.accessToken,
                refreshToken = tokenItem.refreshToken,
            )
        )
    }

    private suspend fun saveOrUpdateIncludeTableToken(tokenItem: TokenItem) {
        val existToken = tokenDao.getToken(tokenItem.accessToken)
        if (existToken == null) {
            insertIntoTableToken(tokenItem)
            Log.d(TAG, "insert token into DB")
        } else {
            updateIntoTableToken(existToken, tokenItem)
            Log.d(TAG, "update token into DB")
        }
    }


//    override suspend fun refresh(refreshItem: RefreshItem): TokenItem {
//        TODO("Not yet implemented")
//    }

    private companion object {
        private const val TAG = "AuthRepositoryImpl"
    }
}