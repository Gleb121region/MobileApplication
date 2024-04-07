package ru.spbstu.mobileapplication.domain.user.entity

import ru.spbstu.mobileapplication.domain.enums.Gender
import java.util.Date

data class UserItem(
    val userId: Int,
    val email: String,
    val firstname: String,
    val lastname: String?,
    val about: String?,
    val gender: Gender?,
    val birthdayDate: Date?,
    val phone: String?,
    val photos: List<String>?,
    var imagePosition: Int = 0
)