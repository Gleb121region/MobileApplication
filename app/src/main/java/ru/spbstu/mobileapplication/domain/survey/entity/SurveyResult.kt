package ru.spbstu.mobileapplication.domain.survey.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.City
import ru.spbstu.mobileapplication.domain.enums.Term

@Parcelize
data class SurveyResult(
    val term: Term?,
    val apartmentTypes: List<ApartmentType>?,
    val city: City?,
    val minArea: Int?,
    val maxArea: Int?,
    val minBudget: Int?,
    val maxBudget: Int?,
    // todo: создать отдельный fragment что все это спрашивать.
    val isAirConditioning: Boolean? = true,
    val isBathtub: Boolean? = true,
    val isDishwasher: Boolean? = true,
    val isFurnitureKitchen: Boolean? = true,
    val isFurnitureRoom: Boolean? = true,
    val isInternet: Boolean? = true,
    val isRefrigerator: Boolean? = true,
    val isShowerCubicle: Boolean? = true,
    val isTV: Boolean? = true,
    val isWashingMachine: Boolean? = true,
) : Parcelable