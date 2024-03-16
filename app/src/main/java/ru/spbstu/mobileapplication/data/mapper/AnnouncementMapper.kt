package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementResponse
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementWithDescriptionResponse
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import javax.inject.Inject

class AnnouncementMapper @Inject constructor() {
    fun mapAnnouncementWithDescriptionResponseToAnnouncementEntity(
        announcementWithDescriptionResponse: AnnouncementWithDescriptionResponse
    ): AnnouncementEntity =
        AnnouncementEntity(
            id = announcementWithDescriptionResponse.id,
            floor = announcementWithDescriptionResponse.floor,
            floorsCount = announcementWithDescriptionResponse.floorsCount,
            totalMeters = announcementWithDescriptionResponse.totalMeters,
            apartmentType = announcementWithDescriptionResponse.apartmentType,
            pricePerMonth = announcementWithDescriptionResponse.pricePerMonth,
            address = announcementWithDescriptionResponse.address,
            underground = announcementWithDescriptionResponse.underground,
            photoUrls = announcementWithDescriptionResponse.photoUrls
        )

    fun mapAnnouncementResponseToAnnouncementEntity(
        announcementResponse: AnnouncementResponse
    ): AnnouncementEntity =
        AnnouncementEntity(
            id = announcementResponse.id,
            floor = announcementResponse.floor,
            floorsCount = announcementResponse.floorsCount,
            totalMeters = announcementResponse.totalMeters,
            apartmentType = announcementResponse.apartmentType,
            pricePerMonth = announcementResponse.pricePerMonth,
            address = announcementResponse.address,
            underground = announcementResponse.underground,
            photoUrls = announcementResponse.photoUrls
        )

}