package ru.spbstu.mobileapplication.data.database.announcement

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.spbstu.mobileapplication.domain.enums.ApartmentType

@Parcelize
@Entity(tableName = "announcements")
data class AnnouncementDbModel(
    @PrimaryKey(autoGenerate = true) val announcementId: Int = 0,
    val floor: Int,
    val floorsCount: Int,
    val totalMeters: Int,
    val apartmentType: ApartmentType,
    val pricePerMonth: Int,
    val address: String,
    val underground: String,
    val photoUrls: List<String>?,
    val isLikedByUser: Boolean,
    val description: String?
) : Parcelable