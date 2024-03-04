package ru.spbstu.mobileapplication.domain.authentication.entity

data class RegisterItem(
    val firstName: String, val email: String, val password: String, val role: String
)