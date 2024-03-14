package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.data.network.survey.model.request.CreateSurveyRequest
import ru.spbstu.mobileapplication.data.network.survey.model.response.GetSurveyResponse
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyAnswersItem
import javax.inject.Inject

class SurveyMapper @Inject constructor() {
    fun mapSurveyAnswersItemToCreateSurveyRequest(surveyAnswersItem: SurveyAnswersItem) =
        CreateSurveyRequest(
            apartmentType = surveyAnswersItem.apartmentType,
            city = surveyAnswersItem.city,
            term = surveyAnswersItem.term,
            minArea = surveyAnswersItem.minArea,
            maxArea = surveyAnswersItem.maxArea,
            minBudget = surveyAnswersItem.minBudget,
            maxBudget = surveyAnswersItem.maxBudget
        )

    fun mapSurveyAnswersItemToAnswerDbModel(surveyAnswersItem: SurveyAnswersItem) =
        AnswerDbModel(
            apartmentType = surveyAnswersItem.apartmentType,
            city = surveyAnswersItem.city,
            term = surveyAnswersItem.term,
            minArea = surveyAnswersItem.minArea,
            maxArea = surveyAnswersItem.maxArea,
            minBudget = surveyAnswersItem.minBudget,
            maxBudget = surveyAnswersItem.maxBudget
        )

    fun mapGetSurveyResponseToSurveyAnswersItem(response: GetSurveyResponse) =
        SurveyAnswersItem(
            apartmentType = response.apartmentType,
            city = response.city,
            term = response.term,
            minArea = response.minArea,
            maxArea = response.maxArea,
            minBudget = response.minBudget,
            maxBudget = response.maxBudget
        )
}