package ru.spbstu.mobileapplication.domain.announcement.entity

import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.City

data class AnnouncementFilterEntity(
    val city: City,
    val underground: String?,
    val district: String?,
    val apartmentTypes: List<ApartmentType>?,
    val maxPricePerMonth: Int?,
    val minPricePerMonth: Int?,
    val maxArea: Int?,
    val minArea: Int?,
    val isRefrigerator: Boolean?,
    val isWashingMachine: Boolean?,
    val isTV: Boolean?,
    val isShowerCubicle: Boolean?,
    val isBathtub: Boolean?,
    val isFurnitureRoom: Boolean?,
    val isFurnitureKitchen: Boolean?,
    val isDishwasher: Boolean?,
    val isAirConditioning: Boolean?,
    val isInternet: Boolean?,
)
