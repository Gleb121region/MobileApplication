package ru.spbstu.mobileapplication.data.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(answer: UserDBModel)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserDBModel>

    @Query("SELECT * FROM users where userId = :userId limit 1")
    suspend fun findUserById(userId: Int): UserDBModel?

    @Update
    suspend fun updateUser(user: UserDBModel)

    @Query("DELETE FROM users Where userId = :userId")
    suspend fun deleteUser(userId: Int)
}