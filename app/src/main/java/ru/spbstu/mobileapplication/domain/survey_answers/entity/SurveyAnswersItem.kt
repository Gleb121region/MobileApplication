package ru.spbstu.mobileapplication.domain.survey_answers.entity

import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.enums.interview.Term


data class SurveyAnswersItem(
    val term: Term,
    val apartmentType: Set<ApartmentType>,
    val city: City,
    val minArea: Int,
    val maxArea: Int,
    val minBudget: Int,
    val maxBudget: Int,
)
