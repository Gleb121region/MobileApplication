package ru.spbstu.mobileapplication.domain.survey.repository

import ru.spbstu.mobileapplication.domain.survey.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyResult

interface SurveyAnswersRepository {
    // network
    suspend fun getFillOutSurvey(): Set<SurveyAnswersItem>
    suspend fun fillOutSurvey(survey: SurveyResult)

    // database
    suspend fun insertAnswersIntoDataBase(survey: SurveyResult)
    suspend fun getAnswersFromDataBase(): List<SurveyAnswersItem>
    suspend fun getLastAnswerFromDataBase(): SurveyAnswersItem

}
