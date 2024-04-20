package ru.spbstu.mobileapplication.data.storage.announcement

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesAnnouncementStorage @Inject constructor(
    context: Context
) : AnnouncementStorage {

    companion object {
        const val ANNOUNCEMENT_PREFS_NAME = "announcement_prefs"
        const val KEY_SELECTED_ANNOUNCEMENT_ID = "selected_announcement_id"
        const val KEY_WAS_WORKED_IN = "was_worked_in"
    }

    private val sharedPreferences = context.getSharedPreferences(
        ANNOUNCEMENT_PREFS_NAME,
        Context.MODE_PRIVATE
    )

    override fun saveAnnouncementId(announcementId: Int) {
        sharedPreferences.edit().putInt(KEY_SELECTED_ANNOUNCEMENT_ID, announcementId).apply()
    }

    override fun getAnnouncementId(): Int {
        return sharedPreferences.getInt(KEY_SELECTED_ANNOUNCEMENT_ID, -1)
    }

    override fun saveTag(tag: String) {
        sharedPreferences.edit().putString(KEY_WAS_WORKED_IN, tag).apply()
    }

    override fun getTag(): String? {
        return sharedPreferences.getString(KEY_WAS_WORKED_IN, null)
    }
}