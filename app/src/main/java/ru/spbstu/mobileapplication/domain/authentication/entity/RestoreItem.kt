package ru.spbstu.mobileapplication.domain.authentication.entity


data class RestoreItem(
    val email: String,
    val newPassword: String,
    val confirmationPassword: String
)