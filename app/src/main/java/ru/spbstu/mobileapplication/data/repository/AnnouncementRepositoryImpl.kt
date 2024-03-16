package ru.spbstu.mobileapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import ru.spbstu.mobileapplication.data.mapper.AnnouncementMapper
import ru.spbstu.mobileapplication.data.network.announcement.AnnouncementApiService
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementWithDescriptionResponse
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementFilterEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
    private val api: AnnouncementApiService, private val mapper: AnnouncementMapper,
    // todo: исправить.
    private val getToken: GetTokenFromLocalStorageUseCase
) : AnnouncementRepository {

    override suspend fun getAnnouncementItem(announcementItemId: Int): AnnouncementEntity {
        val announcementWithDescriptionResponse: AnnouncementWithDescriptionResponse? =
            api.getAnnouncementInfo(announcementItemId, getAccessToken())
        return mapper.mapAnnouncementWithDescriptionResponseToAnnouncementEntity(
            announcementWithDescriptionResponse
                ?: throw RuntimeException("announcementWithDescriptionResponse is null")
        )
    }

    override suspend fun getAnnouncementList(
        request: AnnouncementFilterEntity,
        limit: Int,
        offset: Int,
    ): LiveData<List<AnnouncementEntity>> = liveData {
        val announcementResponseList = api.getFewAnnouncements(
            city = request.city,
            underground = request.underground,
            district = request.district,
            apartmentTypes = request.apartmentTypes,
            maxPricePerMonth = request.maxPricePerMonth.toDouble(),
            minPricePerMonth = request.minPricePerMonth.toDouble(),
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
            token = getAccessToken()
        )
        val announcementEntityList =
            announcementResponseList.map { mapper.mapAnnouncementResponseToAnnouncementEntity(it) }
        emit(announcementEntityList)
    }

    private fun getAccessToken(): String {
        return "Bearer ${getToken().accessToken}"
    }
}