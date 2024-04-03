package ru.spbstu.mobileapplication.domain.announcement.usecases.database

import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementsFromDataBaseUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {
    suspend operator fun invoke() = announcementRepository.getAnnouncements()
}