package ru.spbstu.mobileapplication.domain.user.entity

import java.util.Date

data class UserItem(
    val email: String,
    val firstname: String,
    val lastname: String?,
    val aboutMe: String?,
    val gender: String?,
    val birthdayDate: Date?,
    val phone: String?,
    val photos: Set<String>?
)