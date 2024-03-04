package ru.spbstu.mobileapplication.domain.announcement.repository

import androidx.lifecycle.LiveData
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity


interface AnnouncementRepository {
    suspend fun addAnnouncementItem(shopItem: AnnouncementEntity)
    suspend fun getAnnouncementItem(shopItemId: Int): AnnouncementEntity
    fun getAnnouncementList(): LiveData<List<AnnouncementEntity>>
    suspend fun editAnnouncementItem(shopItem: AnnouncementEntity)
    suspend fun deleteAnnouncementItem(shopItem: AnnouncementEntity)
}