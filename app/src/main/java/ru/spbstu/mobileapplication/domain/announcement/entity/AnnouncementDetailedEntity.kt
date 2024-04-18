package ru.spbstu.mobileapplication.domain.announcement.entity

import android.content.Context
import ru.spbstu.mobileapplication.R
import ru.spbstu.mobileapplication.domain.enums.ApartmentType

data class AnnouncementDetailedEntity(
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
    val description: String?,

    // Условия в квартире
    val isRefrigerator: Boolean,
    val isWashingMachine: Boolean,
    val isTV: Boolean,
    val isShowerCubicle: Boolean,
    val isBathtub: Boolean,
    val isFurnitureRoom: Boolean,
    val isFurnitureKitchen: Boolean,
    val isDishwasher: Boolean,
    val isAirConditioning: Boolean,
    val isInternet: Boolean,

    val isHide: Boolean,

    var currentImagePosition: Int = 0,
    val size: Int = photoUrls.size

) {
    fun getFormattedAddress(): String = "Адрес: $address"

    fun getFormattedTotalMeters(): String = "Общая площадь: $totalMeters м²"

    fun getFormattedPricePerMonth(): String = "Цена за месяц: $pricePerMonth руб."

    fun getFormattedFloorAndFloorsCount(): String = "Этаж: $floor из $floorsCount"

    fun getFormattedPhotoPositionAndPhotosSize(): String = "${currentImagePosition + 1}/$size"


    fun getApartmentTypeRusName(context: Context): String {
        when (apartmentType) {
            ApartmentType.STUDIO -> {
                return "Тип недвижимости:  ${context.getString(R.string.studio)}"
            }

            ApartmentType.ONE_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${context.getString(R.string.one_room_flat)}"
            }

            ApartmentType.TWO_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${context.getString(R.string.two_rooms_flat)}"
            }

            ApartmentType.THREE_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${context.getString(R.string.three_rooms_flat)}"
            }

            ApartmentType.FOUR_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${context.getString(R.string.four_rooms_flat)}"
            }

            ApartmentType.FIVE_ROOM_APARTMENT -> {
                return "Тип недвижимости:  ${context.getString(R.string.five_rooms_flat)}"
            }
        }
    }

    fun getFeatures(): List<String> {
        val features = mutableListOf<String>()
        if (isRefrigerator) features.add("Холодильник")
        if (isWashingMachine) features.add("Стиральная машина")
        if (isTV) features.add("Телевизор")
        if (isShowerCubicle) features.add("Душевая кабина")
        if (isBathtub) features.add("Ванна")
        if (isFurnitureRoom) features.add("Мебель в квартире")
        if (isFurnitureKitchen) features.add("Мебель на кухне")
        if (isDishwasher) features.add("Посудомоечная машина")
        if (isAirConditioning) features.add("Кондиционер")
        if (isInternet) features.add("Интернет")
        if (isHide) features.add("Скрыт")
        return features
    }
}
