package ru.spbstu.mobileapplication.data.repository

import androidx.lifecycle.LiveData
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
) : AnnouncementRepository {
    override suspend fun addAnnouncementItem(shopItem: AnnouncementEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAnnouncementItem(shopItemId: Int): AnnouncementEntity {
        TODO("Not yet implemented")
    }

    override fun getAnnouncementList(): LiveData<List<AnnouncementEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun editAnnouncementItem(shopItem: AnnouncementEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAnnouncementItem(shopItem: AnnouncementEntity) {
        TODO("Not yet implemented")
    }
}