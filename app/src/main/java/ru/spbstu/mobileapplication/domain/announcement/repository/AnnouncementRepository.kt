package ru.spbstu.mobileapplication.domain.announcement.repository

import androidx.lifecycle.LiveData
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementFilterEntity
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem


interface AnnouncementRepository {
//    suspend fun addAnnouncementItem(shopItem: AnnouncementEntity)
    suspend fun getAnnouncementItem(announcementItemId: Int): AnnouncementEntity
    suspend fun getAnnouncementList(request: AnnouncementFilterEntity, limit: Int, offset: Int): LiveData<List<AnnouncementEntity>>
//    suspend fun editAnnouncementItem(shopItem: AnnouncementEntity)
//    suspend fun deleteAnnouncementItem(shopItem: AnnouncementEntity)
}