package ru.spbstu.mobileapplication.domain.announcement.usecases.database

import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class InsertAnnouncementIntoDataBaseUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {
    suspend operator fun invoke(announcement: AnnouncementEntity) =
        announcementRepository.insertAnswersIntoDataBase(announcement)
}