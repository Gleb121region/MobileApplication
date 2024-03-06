package ru.spbstu.mobileapplication.data.database.token

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TokenInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: TokenDbModel)

    @Query("SELECT * FROM tokens where token = :token")
    suspend fun getToken(token: String): TokenDbModel?

    @Query("SELECT * FROM tokens ORDER BY tokenId DESC LIMIT 1")
    suspend fun getLastToken(): TokenDbModel?

    @Update
    suspend fun updateToken(token: TokenDbModel)
}