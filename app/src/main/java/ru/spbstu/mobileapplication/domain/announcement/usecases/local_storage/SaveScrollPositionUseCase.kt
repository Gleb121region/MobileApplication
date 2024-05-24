package ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage

import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementStorageRepository
import javax.inject.Inject

class SaveScrollPositionUseCase @Inject constructor(
    private val announcementStorageRepository: AnnouncementStorageRepository
) {
    operator fun invoke(scrollPosition: Int) {
        announcementStorageRepository.saveScrollPosition(scrollPosition)
    }
}
