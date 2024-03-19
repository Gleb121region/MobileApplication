package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.data.mapper.AnnouncementMapper
import ru.spbstu.mobileapplication.data.mapper.SurveyMapper
import ru.spbstu.mobileapplication.data.network.announcement.AnnouncementApiService
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementWithDescriptionResponse
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
    private val api: AnnouncementApiService,
    private val surveyMapper: SurveyMapper,
    private val announcementMapper: AnnouncementMapper,
) : AnnouncementRepository {

    override suspend fun getAnnouncementItem(
        announcementItemId: Int,
        token: String
    ): AnnouncementEntity {
        val announcementWithDescriptionResponse: AnnouncementWithDescriptionResponse? =
            api.getAnnouncementInfo(announcementItemId, token)
        return announcementMapper.mapAnnouncementWithDescriptionResponseToAnnouncementEntity(
            announcementWithDescriptionResponse
                ?: throw RuntimeException("announcementWithDescriptionResponse is null")
        )
    }

    override suspend fun getAnnouncementList(
        model: AnswerDbModel,
        limit: Int,
        offset: Int,
        token: String
    ): List<AnnouncementEntity> {
        val request = surveyMapper.mapAnswerDbModelToAnnouncementFilterRequest(model)
        val apartmentTypesAsString = request.apartmentTypes?.map { it.name }
        val announcementResponseList = api.getFewAnnouncements(
            city = request.city,
            underground = request.underground,
            district = request.district,
            apartmentTypes = apartmentTypesAsString,
            maxPricePerMonth = request.maxPricePerMonth,
            minPricePerMonth = request.minPricePerMonth,
            maxArea = request.maxArea,
            minArea = request.minArea,
            isRefrigerator = request.isRefrigerator,
            isWashingMachine = request.isWashingMachine,
            isTV = request.isTV,
            isShowerCubicle = request.isShowerCubicle,
            isBathtub = request.isBathtub,
            isFurnitureRoom = request.isFurnitureRoom,
            isFurnitureKitchen = request.isFurnitureKitchen,
            isAirConditioning = request.isAirConditioning,
            isDishwasher = request.isDishwasher,
            isInternet = request.isInternet,
            limit = limit,
            offset = offset,
            token = token
        )

        return announcementResponseList.map {
            announcementMapper.mapAnnouncementResponseToAnnouncementEntity(
                it
            )
        }
    }


}