package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.storage.announcement.SharedPreferencesAnnouncementStorage
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementStorageRepository
import javax.inject.Inject

class AnnouncementStorageRepositoryImpl @Inject constructor(
    private val storage: SharedPreferencesAnnouncementStorage
) : AnnouncementStorageRepository {
    override fun saveAnnouncementId(id: Int) = storage.saveAnnouncementId(id)


    override fun getAnnouncementId(): Int = storage.getAnnouncementId()


    override fun saveTag(tag: String) = storage.saveTag(tag)


    override fun getTag(): String? = storage.getTag()

}