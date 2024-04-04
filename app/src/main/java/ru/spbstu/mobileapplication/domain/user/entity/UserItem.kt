package ru.spbstu.mobileapplication.domain.user.entity

import java.util.Date

data class UserItem(
    val email: String,
    val firstname: String,
    val lastname: String?,
    val about: String?,
    val gender: String?,
    val birthdayDate: Date?,
    val phone: String?,
    val photos: List<String>?,
    var imagePosition: Int = 0
)