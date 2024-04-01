package ru.spbstu.mobileapplication.domain.announcement.repository

import ru.spbstu.mobileapplication.data.database.announcement.AnnouncementDbModel
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity


interface AnnouncementRepository {
    // network
    //    suspend fun addAnnouncementItem(shopItem: AnnouncementEntity)
    suspend fun getAnnouncementItem(announcementItemId: Int, token: String): AnnouncementEntity
    suspend fun getAnnouncementList(
        model: AnswerDbModel,
        limit: Int,
        offset: Int,
        token: String
    ): List<AnnouncementEntity>
//    suspend fun editAnnouncementItem(shopItem: AnnouncementEntity)
//    suspend fun deleteAnnouncementItem(shopItem: AnnouncementEntity)

    // database
    suspend fun insertAnswersIntoDataBase(announcement: AnnouncementEntity)
    suspend fun getAnnouncementByAnnouncementId(announcementId: Int): AnnouncementDbModel?
    suspend fun getAnnouncements(): List<AnnouncementDbModel>
}