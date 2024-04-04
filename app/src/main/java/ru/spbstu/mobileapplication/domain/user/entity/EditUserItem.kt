package ru.spbstu.mobileapplication.domain.user.entity

import ru.spbstu.mobileapplication.domain.enums.Gender
import java.util.Date

data class EditUserItem(
    val about: String?,
    val birthdayDate: Date?,
    val firstname: String?,
    val gender: Gender?,
    val lastname: String?,
    val phone: String?
)