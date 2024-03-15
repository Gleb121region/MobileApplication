package ru.spbstu.mobileapplication.domain.announcement.entity

import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City

data class AnnouncementEntity(
    val city: City,
    val underground: String,
    val district: String,
    val roomsCounts: Set<ApartmentType>,
    val maxPricePerMonth: Int,
    val minPricePerMonth: Int,
    val isRefrigerator: Boolean,
    val isWashingMachine: Boolean,
    val isTV: Boolean,
    val isShowerCubicle: Boolean,
    val isBathtub: Boolean,
    val isFurnitureRoom: Boolean,
    val isFurnitureKitchen: Boolean,
    val isDishwasher: Boolean,
    val isAirConditioning: Boolean,
    val isInternet: Boolean,
)
