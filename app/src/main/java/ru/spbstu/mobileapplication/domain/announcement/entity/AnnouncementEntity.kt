package ru.spbstu.mobileapplication.domain.announcement.entity

import android.content.Context
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.domain.enums.ApartmentType

data class AnnouncementEntity(
    val id: Int,
    val floor: Int,
    val floorsCount: Int,
    val totalMeters: Int,
    val apartmentType: ApartmentType,
    val pricePerMonth: Int,
    val address: String,
    val underground: String,
    val photoUrls: List<String>,
    val isLikedByUser: Boolean = false,
    val description: String?,
    val phoneNumber: String = "89999999999",
    var currentImagePosition: Int = 0
) {
    fun getFormattedTotalMeters(): String = "$totalMeters м²"

    fun getFormattedPricePerMonth(): String = "$pricePerMonth ₽/мес."

    fun getFormattedFloorAndFloorsCount(): String = "$floor/$floorsCount"

    fun getApartmentTypeRusName(context: Context): String {
        return when (apartmentType) {
            ApartmentType.STUDIO -> {
                context.getString(R.string.studio)
            }

            ApartmentType.ONE_ROOM_APARTMENT -> {
                context.getString(R.string.one_room_flat)
            }

            ApartmentType.TWO_ROOM_APARTMENT -> {
                context.getString(R.string.two_rooms_flat)
            }

            ApartmentType.THREE_ROOM_APARTMENT -> {
                context.getString(R.string.three_rooms_flat)
            }

            ApartmentType.FOUR_ROOM_APARTMENT -> {
                context.getString(R.string.four_rooms_flat)
            }

            ApartmentType.FIVE_ROOM_APARTMENT -> {
                context.getString(R.string.five_rooms_flat)
            }
        }
    }
}
