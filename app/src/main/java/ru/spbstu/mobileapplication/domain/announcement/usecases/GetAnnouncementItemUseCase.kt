package ru.spbstu.mobileapplication.domain.announcement.usecases

import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementItemUseCase @Inject constructor(
    private val shopListRepository: AnnouncementRepository
) {

    suspend fun getAnnouncementItem(shopItemId: Int, token: String): AnnouncementEntity {
        return shopListRepository.getAnnouncementItem(shopItemId, token)
    }
}