package ru.spbstu.mobileapplication.domain.user.entity

import java.util.Date

data class UserItem(
    val id: Int,
    val email: String,
    val password: String,
    val role: String,
    val firstname: String,
    val lastname: String?,
    val aboutMe: String?,
    val gender: String?,
    val birthdayDate: Date?,
    val phone: String?,
    val linkVK: String?,
)