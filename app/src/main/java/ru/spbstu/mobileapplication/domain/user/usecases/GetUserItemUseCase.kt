package ru.spbstu.mobileapplication.domain.user.usecases

import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetUserItemUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {

    suspend fun getAnnouncementItem(shopItemId: Int): AnnouncementEntity {
        return announcementRepository.getAnnouncementItem(shopItemId)
    }
}