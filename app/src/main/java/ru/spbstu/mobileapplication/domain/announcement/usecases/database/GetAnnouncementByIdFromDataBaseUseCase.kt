package ru.spbstu.mobileapplication.domain.announcement.usecases.database

import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementByIdFromDataBaseUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {
    suspend operator fun invoke(announcementId: Int) =
        announcementRepository.getAnnouncementByAnnouncementId(announcementId)
}