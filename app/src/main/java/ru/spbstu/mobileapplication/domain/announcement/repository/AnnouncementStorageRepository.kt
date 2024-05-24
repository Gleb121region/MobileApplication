package ru.spbstu.mobileapplication.domain.announcement.repository

interface AnnouncementStorageRepository {
    fun saveAnnouncementId(id: Int)
    fun getAnnouncementId(): Int
    fun saveTag(tag: String)
    fun getTag(): String?
    fun saveScrollPosition(position: Int)
    fun getScrollPosition(): Int
}