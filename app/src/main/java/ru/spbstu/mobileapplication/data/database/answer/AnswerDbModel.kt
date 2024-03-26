package ru.spbstu.mobileapplication.data.database.answer

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.City
import ru.spbstu.mobileapplication.domain.enums.Term

@Parcelize
@Entity(tableName = "answers")
data class AnswerDbModel(
    @PrimaryKey(autoGenerate = true) val answerId: Int = 0,
    val term: Term,
    val city: City,
    val apartmentTypes: List<ApartmentType>,
    val minArea: Int,
    val maxArea: Int,
    val minBudget: Int,
    val maxBudget: Int,
    val isAirConditioning: Boolean,
    val isBathtub: Boolean,
    val isDishwasher: Boolean,
    val isFurnitureKitchen: Boolean,
    val isFurnitureRoom: Boolean,
    val isInternet: Boolean,
    val isRefrigerator: Boolean,
    val isShowerCubicle: Boolean,
    val isTV: Boolean,
    val isWashingMachine: Boolean,
) : Parcelable