package ru.spbstu.mobileapplication.data.database.announcement

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnnouncementInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnnouncement(announcement: AnnouncementDbModel)

    @Query("SELECT * FROM announcements limit 10 offset 0")
    fun getAllAnnouncements(): List<AnnouncementDbModel>

    @Query("SELECT * FROM announcements where announcementId = :announcementId")
    suspend fun findAnnouncementById(announcementId: Int): AnnouncementDbModel?
}