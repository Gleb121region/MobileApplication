package ru.spbstu.mobileapplication.domain.survey.entity

import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.City
import ru.spbstu.mobileapplication.domain.enums.Term


data class SurveyAnswersItem(
    val term: Term,
    val apartmentTypes: List<ApartmentType>,
    val city: City,
    val minArea: Int,
    val maxArea: Int,
    val minBudget: Int,
    val maxBudget: Int,
    val isAirConditioning: Boolean,
    val isBathtub: Boolean,
    val isDishwasher: Boolean,
    val isFurnitureKitchen: Boolean,
    val isFurnitureRoom: Boolean,
    val isInternet: Boolean,
    val isRefrigerator: Boolean,
    val isShowerCubicle: Boolean,
    val isTV: Boolean,
    val isWashingMachine: Boolean,
)
