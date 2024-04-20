package ru.spbstu.mobileapplication.domain.announcement.usecases.local_storage

import ru.spbstu.mobileapplication.data.storage.announcement.AnnouncementStorage
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementStorageRepository
import javax.inject.Inject

class GetTagUseCase @Inject constructor(
    private val announcementStorageRepository: AnnouncementStorageRepository
) {
    operator fun invoke() = announcementStorageRepository.getTag()

}