package ru.spbstu.mobileapplication.data.database.answer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnswerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswerInfoDao)

    @Query("DELETE FROM answers")
    suspend fun deleteToken()
}