package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.database.announcement.AnnouncementDbModel
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementResponse
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementWithDescriptionResponse
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementDetailedEntity
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import javax.inject.Inject

class AnnouncementMapper @Inject constructor() {
    fun mapAnnouncementWithDescriptionResponseToAnnouncementEntity(
        announcementWithDescriptionResponse: AnnouncementWithDescriptionResponse
    ): AnnouncementDetailedEntity = AnnouncementDetailedEntity(
        id = announcementWithDescriptionResponse.id,
        floor = announcementWithDescriptionResponse.floor,
        floorsCount = announcementWithDescriptionResponse.floorsCount,
        totalMeters = announcementWithDescriptionResponse.totalMeters,
        apartmentType = announcementWithDescriptionResponse.apartmentType,
        pricePerMonth = announcementWithDescriptionResponse.pricePerMonth,
        address = announcementWithDescriptionResponse.address,
        underground = announcementWithDescriptionResponse.underground,
        photoUrls = announcementWithDescriptionResponse.photoUrls,
        isLikedByUser = announcementWithDescriptionResponse.isLikedByUser,
        description = announcementWithDescriptionResponse.description,
        isRefrigerator = announcementWithDescriptionResponse.isRefrigerator,
        isWashingMachine = announcementWithDescriptionResponse.isWashingMachine,
        isTV = announcementWithDescriptionResponse.isTV,
        isShowerCubicle = announcementWithDescriptionResponse.isShowerCubicle,
        isBathtub = announcementWithDescriptionResponse.isBathtub,
        isFurnitureRoom = announcementWithDescriptionResponse.isFurnitureRoom,
        isFurnitureKitchen = announcementWithDescriptionResponse.isFurnitureKitchen,
        isAirConditioning = announcementWithDescriptionResponse.isAirConditioning,
        isDishwasher = announcementWithDescriptionResponse.isDishwasher,
        isInternet = announcementWithDescriptionResponse.isInternet,
        isHide = announcementWithDescriptionResponse.isHide
    )

    fun mapAnnouncementResponseToAnnouncementEntity(
        announcementResponse: AnnouncementResponse
    ): AnnouncementEntity = AnnouncementEntity(
        id = announcementResponse.id,
        floor = announcementResponse.floor,
        floorsCount = announcementResponse.floorsCount,
        totalMeters = announcementResponse.totalMeters,
        apartmentType = announcementResponse.apartmentType,
        pricePerMonth = announcementResponse.pricePerMonth,
        address = announcementResponse.address,
        underground = announcementResponse.underground,
        photoUrls = announcementResponse.photoUrls,
        isLikedByUser = announcementResponse.isLikedByUser,
        description = null
    )

    fun mapAnnouncementEntityToAnnouncementDbModel(
        announcement: AnnouncementEntity
    ): AnnouncementDbModel = AnnouncementDbModel(
        announcementId = announcement.id,
        floor = announcement.floor,
        floorsCount = announcement.floorsCount,
        totalMeters = announcement.totalMeters,
        apartmentType = announcement.apartmentType,
        pricePerMonth = announcement.pricePerMonth,
        address = announcement.address,
        underground = announcement.underground,
        photoUrls = announcement.photoUrls,
        isLikedByUser = announcement.isLikedByUser,
        description = announcement.description
    )
}