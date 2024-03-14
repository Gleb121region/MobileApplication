package ru.spbstu.mobileapplication.data.database.answer

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.enums.interview.Term

@Parcelize
@Entity(tableName = "answers")
data class AnswerDbModel(
    @PrimaryKey(autoGenerate = true) val answerId: Int = 0,
    val term: Term,
    val city: City,
    val apartmentType: Set<ApartmentType>,
    val minArea: Int,
    val maxArea: Int,
    val minBudget: Int,
    val maxBudget: Int,
) : Parcelable