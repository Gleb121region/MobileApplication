package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.data.network.announcement.model.request.FlatRequest
import ru.spbstu.mobileapplication.data.network.survey.model.request.CreateSurveyRequest
import ru.spbstu.mobileapplication.data.network.survey.model.response.GetSurveyResponse
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult
import javax.inject.Inject

class SurveyMapper @Inject constructor() {

    fun mapSurveyResultToAnswerDbModel(survey: SurveyResult): AnswerDbModel =
        AnswerDbModel(
            apartmentType = survey.apartmentTypes
                ?: throw RuntimeException("apartmentType is null"),
            city = survey.city ?: throw RuntimeException("city is null"),
            term = survey.term ?: throw RuntimeException("term is null"),
            minArea = survey.minArea ?: throw RuntimeException("minArea is null"),
            maxArea = survey.maxArea ?: throw RuntimeException("maxArea is null"),
            minBudget = survey.minBudget ?: throw RuntimeException("minBudget is null"),
            maxBudget = survey.maxBudget ?: throw RuntimeException("maxBudget is null"),
        )

    fun mapSurveyResultToCreateSurveyRequest(survey: SurveyResult): CreateSurveyRequest =
        CreateSurveyRequest(
            apartmentType = survey.apartmentTypes
                ?: throw RuntimeException("apartmentType is null"),
            city = survey.city ?: throw RuntimeException("city is null"),
            term = survey.term ?: throw RuntimeException("term is null"),
            minArea = survey.minArea ?: throw RuntimeException("minArea is null"),
            maxArea = survey.maxArea ?: throw RuntimeException("maxArea is null"),
            minBudget = survey.minBudget ?: throw RuntimeException("minBudget is null"),
            maxBudget = survey.maxBudget ?: throw RuntimeException("maxBudget is null"),
        )

    fun mapSurveyAnswersItemToAnswerDbModel(survey: SurveyAnswersItem): AnswerDbModel =
        AnswerDbModel(
            apartmentType = survey.apartmentType,
            city = survey.city,
            term = survey.term,
            minArea = survey.minArea,
            maxArea = survey.maxArea,
            minBudget = survey.minBudget,
            maxBudget = survey.maxBudget
        )

    fun mapGetSurveyResponseToSurveyAnswersItem(response: GetSurveyResponse): SurveyAnswersItem =
        SurveyAnswersItem(
            apartmentType = response.apartmentType,
            city = response.city,
            term = response.term,
            minArea = response.minArea,
            maxArea = response.maxArea,
            minBudget = response.minBudget,
            maxBudget = response.maxBudget
        )

    fun mapAnswerDbModelToSurveyResult(model: AnswerDbModel): SurveyAnswersItem =
        SurveyAnswersItem(
            apartmentType = model.apartmentType,
            city = model.city,
            term = model.term,
            minArea = model.minArea,
            maxArea = model.maxArea,
            minBudget = model.minBudget,
            maxBudget = model.maxBudget
        )

    fun mapAnswerDbModelToFlatRequest(model: AnswerDbModel): FlatRequest =
        FlatRequest(
            city = model.city,
            underground = null,
            district = null,
            apartmentTypes = model.apartmentType,
            maxPricePerMonth = model.maxBudget.toDouble(),
            minPricePerMonth = model.minBudget.toDouble(),
            isRefrigerator = null,
            isWashingMachine = null,
            isTV = null,
            isShowerCubicle = null,
            isBathtub = null,
            isFurnitureRoom = null,
            isFurnitureKitchen = null,
            isAirConditioning = null,
            isDishwasher = null,
            isInternet = null
        )

}