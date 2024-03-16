package ru.spbstu.mobileapplication.domain.announcement.usecases

import androidx.lifecycle.LiveData
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementFilterEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementListUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {

    suspend operator fun invoke(
        request: AnnouncementFilterEntity, limit: Int = 10, offset: Int = 0
    ): LiveData<List<AnnouncementEntity>> {
        return announcementRepository.getAnnouncementList(
            request, limit, offset
        )
    }
}