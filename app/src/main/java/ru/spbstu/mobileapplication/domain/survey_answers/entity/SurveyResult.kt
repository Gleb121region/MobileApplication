package ru.spbstu.mobileapplication.domain.survey_answers.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.enums.interview.Term

@Parcelize
data class SurveyResult(
    val term: Term?,
    val apartmentTypes: Set<ApartmentType>?,
    val city: City?,
    val minArea: Int?,
    val maxArea: Int?,
    val minBudget: Int?,
    val maxBudget: Int?,
) : Parcelable