package ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage

import ru.spbstu.mobileapplication.data.storage.announcement.AnnouncementStorage
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementStorageRepository
import javax.inject.Inject

class SaveTagUseCase @Inject constructor(
    private val announcementStorageRepository: AnnouncementStorageRepository
) {
    operator fun invoke(tag: String) {
        announcementStorageRepository.saveTag(tag)
    }
}