package ru.spbstu.mobileapplication.data.database.answer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswerDbModel)

    @Query("SELECT * FROM answers")
    suspend fun getAnswers(): List<AnswerDbModel>

    @Query("SELECT * FROM answers ORDER BY answerId DESC LIMIT 1")
    suspend fun findLastAnswer(): AnswerDbModel

    @Query("SELECT * FROM answers where answerId = :answerId")
    suspend fun findAnswerById(answerId: Int): AnswerDbModel?

    @Update
    suspend fun updateAnswer(answer: AnswerDbModel)
}