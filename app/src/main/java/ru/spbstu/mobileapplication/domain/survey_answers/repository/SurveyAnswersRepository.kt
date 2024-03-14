package ru.spbstu.mobileapplication.domain.survey_answers.repository

import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.enums.interview.Term
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyAnswersItem

interface SurveyAnswersRepository {
    // network
    suspend fun getFillOutSurvey(): Set<SurveyAnswersItem>
    suspend fun fillOutSurvey(survey: SurveyAnswersItem)

    // local
    // enums
    suspend fun fillOutSurveyTerm(term: Term)
    suspend fun fillOutSurveyApartmentType(apartmentType: Set<ApartmentType>)
    suspend fun fillOutSurveyCity(city: City)

    // Int
    suspend fun fillOutSurveyBudget(minBudget: Int, maxBudget: Int)
    suspend fun fillOutSurveyArea(minArea: Int, maxArea: Int)

}
