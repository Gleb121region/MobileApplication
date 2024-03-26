package ru.spbstu.mobileapplication.data.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesStorage @Inject constructor(
    private val context: Context
) : TokenStorage, IsUserFirstTimeStorage {

    companion object {
        const val ENCRYPTED_SHARED_PREFS_NAME = "encryptedSharedPreferences"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        ENCRYPTED_SHARED_PREFS_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveToken(tokenItem: TokenItem) {
        sharedPreferences.edit().putString(TokenStorage.TOKEN_KEY, tokenItem.accessToken)
            .putString(TokenStorage.REFRESH_TOKEN_KEY, tokenItem.refreshToken).apply()
    }

    override fun getToken(): TokenItem = TokenItem(
        sharedPreferences.getString(TokenStorage.TOKEN_KEY, "").toString(),
        sharedPreferences.getString(TokenStorage.REFRESH_TOKEN_KEY, "").toString(),
    )

    override fun deleteToken() {
        sharedPreferences.edit().remove(TokenStorage.TOKEN_KEY)
            .remove(TokenStorage.REFRESH_TOKEN_KEY).apply()
    }

    override fun changeFirstTimeStatus() {
        sharedPreferences.edit().putBoolean(IsUserFirstTimeStorage.FIRST_TIME_STATUS, false).apply()
    }

    override fun getFirstTimeStatus(): Boolean =
        sharedPreferences.getBoolean(IsUserFirstTimeStorage.FIRST_TIME_STATUS, true)

}