package ru.spbstu.mobileapplication.domain.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class FeedbackType : Parcelable {
    LIKE,
    DISLIKE,
    SKIP,
    DEFAULT
}