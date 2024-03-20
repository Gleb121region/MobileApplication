package ru.spbstu.mobileapplication.domain.announcement.entity

import ru.spbstu.mobileapplication.domain.enums.ApartmentType

data class AnnouncementEntity(
    val id: Int,
    val floor: Int,
    val floorsCount: Int,
    val totalMeters: Int,
    val apartmentType: ApartmentType,
    val pricePerMonth: Int,
    val address: String,
    val underground: String,
    val photoUrls: List<String>,
)