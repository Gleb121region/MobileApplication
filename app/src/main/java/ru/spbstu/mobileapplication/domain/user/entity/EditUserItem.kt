package ru.spbstu.mobileapplication.domain.user.entity

import ru.spbstu.mobileapplication.domain.enums.Gender
import java.util.Date

data class EditUserItem(
    val firstname: String?,
    val lastname: String?,
    val about: String?,
    val birthdayDate: Date?,
    val gender: Gender?,
    val phone: String?
)