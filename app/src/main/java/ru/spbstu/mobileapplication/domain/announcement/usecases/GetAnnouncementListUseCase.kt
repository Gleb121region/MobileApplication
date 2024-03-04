package ru.spbstu.mobileapplication.domain.announcement.usecases

import androidx.lifecycle.LiveData
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementListUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {

    fun getAnnouncementList(): LiveData<List<AnnouncementEntity>> {
        return announcementRepository.getAnnouncementList()
    }
}