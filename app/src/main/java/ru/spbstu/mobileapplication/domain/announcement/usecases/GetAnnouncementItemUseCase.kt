package ru.spbstu.mobileapplication.domain.announcement.usecases

import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementDetailedEntity
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementItemUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {

    suspend operator fun invoke(announcementId: Int, token: String): AnnouncementDetailedEntity {
        return announcementRepository.getAnnouncementItem(announcementId, token)
    }
}