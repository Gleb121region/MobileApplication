package ru.spbstu.mobileapplication.domain.survey.repository

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyResult

interface SurveyAnswersRepository {
    // network
    suspend fun getFillOutSurvey(token: String): Set<SurveyAnswersItem>
    suspend fun fillOutSurvey(survey: SurveyResult, token: String)

    // database
    suspend fun insertAnswersIntoDataBase(survey: SurveyResult)
    suspend fun getAnswersFromDataBase(): List<SurveyAnswersItem>
    suspend fun getLastAnswerFromDataBase(): AnswerDbModel

}
