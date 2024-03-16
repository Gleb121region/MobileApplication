package ru.spbstu.mobileapplication.domain.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ApartmentType : Parcelable {
    STUDIO,
    ONE_ROOM_APARTMENT,
    TWO_ROOM_APARTMENT,
    THREE_ROOM_APARTMENT,
    FOUR_ROOM_APARTMENT,
    FIVE_ROOM_APARTMENT
}