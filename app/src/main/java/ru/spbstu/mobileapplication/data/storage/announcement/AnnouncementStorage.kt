package ru.spbstu.mobileapplication.data.storage.announcement

interface AnnouncementStorage {
    fun saveAnnouncementId(announcementId: Int)
    fun getAnnouncementId(): Int
    fun saveTag(tag: String)
    fun getTag(): String?
}