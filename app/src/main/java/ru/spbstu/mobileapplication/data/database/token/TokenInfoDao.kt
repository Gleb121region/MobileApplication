package ru.spbstu.mobileapplication.data.database.token

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TokenInfoDao {
//  todo: add Token ( After sign up or after sign in )
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: TokenDbModel)

//  todo: remove Token ( After refresh token, restore token)
    @Query("DELETE FROM tokens")
    suspend fun deleteToken()
}