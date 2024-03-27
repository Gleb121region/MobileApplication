package ru.spbstu.mobileapplication.domain.announcement.entity

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
    val isLikedByUser: Boolean,
    var currentImagePosition: Int = 0
) {
    fun getFormattedAddress(): String = "Адрес: $address"

    fun getFormattedTotalMeters(): String = "Общая площадь: $totalMeters м²"

    fun getFormattedPricePerMonth(): String = "Цена за месяц: $pricePerMonth руб."

    fun getFormattedFloorAndFloorsCount(): String = "Этаж: $floor из $floorsCount"

    fun getApartmentTypeRusName(): String {
        when (apartmentType) {
            ApartmentType.STUDIO -> {
                return "Тип недвижимости:  ${R.string.studio}"
            }

            ApartmentType.ONE_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${R.string.one_room_flat}"
            }

            ApartmentType.TWO_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${R.string.two_rooms_flat}"
            }

            ApartmentType.THREE_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${R.string.three_rooms_flat}"
            }

            ApartmentType.FOUR_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${R.string.four_rooms_flat}"
            }

            ApartmentType.FIVE_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${R.string.five_rooms_flat}"
            }
        }
    }
}
