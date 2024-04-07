package ru.spbstu.mobileapplication.data.database.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.spbstu.mobileapplication.domain.enums.Gender
import java.util.Date

@Parcelize
@Entity(tableName = "users")
data class UserDBModel(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val firstname: String,
    val lastname: String?,
    val about: String?,
    val gender: Gender?,
    val birthdayDate: Date?,
    val phone: String?,
    val email: String,
    val photos: List<String>?
) : Parcelable