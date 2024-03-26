package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.data.network.survey.model.request.CreateSurveyRequest
import ru.spbstu.mobileapplication.data.network.survey.model.response.GetSurveyResponse
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementFilterEntity
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyResult
import javax.inject.Inject

class SurveyMapper @Inject constructor() {

    fun mapSurveyResultToAnswerDbModel(survey: SurveyResult): AnswerDbModel = AnswerDbModel(
        apartmentTypes = survey.apartmentTypes ?: throw RuntimeException("apartmentType is null"),
        city = survey.city ?: throw RuntimeException("city is null"),
        term = survey.term ?: throw RuntimeException("term is null"),
        minArea = survey.minArea ?: throw RuntimeException("minArea is null"),
        maxArea = survey.maxArea ?: throw RuntimeException("maxArea is null"),
        minBudget = survey.minBudget ?: throw RuntimeException("minBudget is null"),
        maxBudget = survey.maxBudget ?: throw RuntimeException("maxBudget is null"),
        isRefrigerator = survey.isRefrigerator ?: throw RuntimeException("isRefrigerator is null"),
        isWashingMachine = survey.isWashingMachine
            ?: throw RuntimeException("isWashingMachine is null"),
        isTV = survey.isTV ?: throw RuntimeException("isTV is null"),
        isShowerCubicle = survey.isShowerCubicle
            ?: throw RuntimeException("isShowerCubicle is null"),
        isBathtub = survey.isBathtub ?: throw RuntimeException("isBathtub is null"),
        isFurnitureRoom = survey.isFurnitureRoom
            ?: throw RuntimeException("isFurnitureRoom is null"),
        isFurnitureKitchen = survey.isFurnitureKitchen
            ?: throw RuntimeException("isFurnitureKitchen is null"),
        isAirConditioning = survey.isAirConditioning
            ?: throw RuntimeException("isAirConditioning is null"),
        isDishwasher = survey.isDishwasher ?: throw RuntimeException("isDishwasher is null"),
        isInternet = survey.isInternet ?: throw RuntimeException("isInternet is null"),
    )

    fun mapSurveyResultToCreateSurveyRequest(survey: SurveyResult): CreateSurveyRequest =
        CreateSurveyRequest(
            apartmentTypes = survey.apartmentTypes
                ?: throw RuntimeException("apartmentType is null"),
            city = survey.city ?: throw RuntimeException("city is null"),
            term = survey.term ?: throw RuntimeException("term is null"),
            minArea = survey.minArea ?: throw RuntimeException("minArea is null"),
            maxArea = survey.maxArea ?: throw RuntimeException("maxArea is null"),
            minBudget = survey.minBudget ?: throw RuntimeException("minBudget is null"),
            maxBudget = survey.maxBudget ?: throw RuntimeException("maxBudget is null"),
            isRefrigerator = survey.isRefrigerator
                ?: throw RuntimeException("isRefrigerator is null"),
            isWashingMachine = survey.isWashingMachine
                ?: throw RuntimeException("isWashingMachine is null"),
            isTV = survey.isTV ?: throw RuntimeException("isTV is null"),
            isShowerCubicle = survey.isShowerCubicle
                ?: throw RuntimeException("isShowerCubicle is null"),
            isBathtub = survey.isBathtub ?: throw RuntimeException("isBathtub is null"),
            isFurnitureRoom = survey.isFurnitureRoom
                ?: throw RuntimeException("isFurnitureRoom is null"),
            isFurnitureKitchen = survey.isFurnitureKitchen
                ?: throw RuntimeException("isFurnitureKitchen is null"),
            isAirConditioning = survey.isAirConditioning
                ?: throw RuntimeException("isAirConditioning is null"),
            isDishwasher = survey.isDishwasher ?: throw RuntimeException("isDishwasher is null"),
            isInternet = survey.isInternet ?: throw RuntimeException("isInternet is null"),
        )

    fun mapSurveyAnswersItemToAnswerDbModel(survey: SurveyAnswersItem): AnswerDbModel =
        AnswerDbModel(
            apartmentTypes = survey.apartmentTypes,
            city = survey.city,
            term = survey.term,
            minArea = survey.minArea,
            maxArea = survey.maxArea,
            minBudget = survey.minBudget,
            maxBudget = survey.maxBudget,
            isRefrigerator = survey.isRefrigerator,
            isWashingMachine = survey.isWashingMachine,
            isTV = survey.isTV,
            isShowerCubicle = survey.isShowerCubicle,
            isBathtub = survey.isBathtub,
            isFurnitureRoom = survey.isFurnitureRoom,
            isFurnitureKitchen = survey.isFurnitureKitchen,
            isAirConditioning = survey.isAirConditioning,
            isDishwasher = survey.isDishwasher,
            isInternet = survey.isInternet,
        )

    fun mapSurveyAnswersItemToAnnouncementFilterEntity(survey: SurveyAnswersItem): AnnouncementFilterEntity =
        AnnouncementFilterEntity(
            city = survey.city,
            underground = "",
            district = "",
            apartmentTypes = survey.apartmentTypes,
            maxPricePerMonth = survey.maxBudget,
            minPricePerMonth = survey.minBudget,
            maxArea = survey.maxArea,
            minArea = survey.minArea,
            isRefrigerator = survey.isRefrigerator,
            isWashingMachine = survey.isWashingMachine,
            isTV = survey.isTV,
            isShowerCubicle = survey.isShowerCubicle,
            isBathtub = survey.isBathtub,
            isFurnitureRoom = survey.isFurnitureRoom,
            isFurnitureKitchen = survey.isFurnitureKitchen,
            isAirConditioning = survey.isAirConditioning,
            isDishwasher = survey.isDishwasher,
            isInternet = survey.isInternet,
        )

    fun mapGetSurveyResponseToSurveyAnswersItem(response: GetSurveyResponse): SurveyAnswersItem =
        SurveyAnswersItem(
            apartmentTypes = response.apartmentTypes,
            city = response.city,
            term = response.term,
            minArea = response.minArea,
            maxArea = response.maxArea,
            minBudget = response.minBudget,
            maxBudget = response.maxBudget,
            isRefrigerator = response.isRefrigerator,
            isWashingMachine = response.isWashingMachine,
            isTV = response.isTV,
            isShowerCubicle = response.isShowerCubicle,
            isBathtub = response.isBathtub,
            isFurnitureRoom = response.isFurnitureRoom,
            isFurnitureKitchen = response.isFurnitureKitchen,
            isAirConditioning = response.isAirConditioning,
            isDishwasher = response.isDishwasher,
            isInternet = response.isInternet,
        )

    fun mapAnswerDbModelToSurveyResult(model: AnswerDbModel): SurveyAnswersItem = SurveyAnswersItem(
        apartmentTypes = model.apartmentTypes,
        city = model.city,
        term = model.term,
        minArea = model.minArea,
        maxArea = model.maxArea,
        minBudget = model.minBudget,
        maxBudget = model.maxBudget,
        isRefrigerator = model.isRefrigerator,
        isWashingMachine = model.isWashingMachine,
        isTV = model.isTV,
        isShowerCubicle = model.isShowerCubicle,
        isBathtub = model.isBathtub,
        isFurnitureRoom = model.isFurnitureRoom,
        isFurnitureKitchen = model.isFurnitureKitchen,
        isAirConditioning = model.isAirConditioning,
        isDishwasher = model.isDishwasher,
        isInternet = model.isInternet,
    )

    fun mapAnswerDbModelToAnnouncementFilterRequest(model: AnswerDbModel): AnnouncementFilterEntity =
        AnnouncementFilterEntity(
            city = model.city,
            underground = null,
            district = null,
            apartmentTypes = model.apartmentTypes,
            maxPricePerMonth = model.maxBudget,
            minPricePerMonth = model.minBudget,
            maxArea = model.maxArea,
            minArea = model.minArea,
            isRefrigerator = model.isRefrigerator,
            isWashingMachine = model.isWashingMachine,
            isTV = model.isTV,
            isShowerCubicle = model.isShowerCubicle,
            isBathtub = model.isBathtub,
            isFurnitureRoom = model.isFurnitureRoom,
            isFurnitureKitchen = model.isFurnitureKitchen,
            isAirConditioning = model.isAirConditioning,
            isDishwasher = model.isDishwasher,
            isInternet = model.isInternet
        )

}