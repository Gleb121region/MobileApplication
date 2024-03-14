package ru.spbstu.mobileapplication.domain.enums.interview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Purpose : Parcelable {
    LEASE,
    RENT
}