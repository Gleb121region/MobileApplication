package ru.spbstu.mobileapplication.data.network.survey.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.City
import ru.spbstu.mobileapplication.domain.enums.Term

data class CreateSurveyRequest(
    @SerializedName("apartmentTypes") @Expose val apartmentTypes: List<ApartmentType>,
    @SerializedName("city") @Expose val city: City,
    @SerializedName("term") @Expose val term: Term,
    @SerializedName("maxArea") @Expose val maxArea: Int,
    @SerializedName("maxBudget") @Expose val maxBudget: Int,
    @SerializedName("minArea") @Expose val minArea: Int,
    @SerializedName("minBudget") @Expose val minBudget: Int,
    @SerializedName("isAirConditioning") @Expose  val isAirConditioning: Boolean,
    @SerializedName("isBathtub") @Expose  val isBathtub: Boolean,
    @SerializedName("isDishwasher") @Expose  val isDishwasher: Boolean,
    @SerializedName("isFurnitureKitchen") @Expose  val isFurnitureKitchen: Boolean,
    @SerializedName("isFurnitureRoom") @Expose  val isFurnitureRoom: Boolean,
    @SerializedName("isInternet") @Expose  val isInternet: Boolean,
    @SerializedName("isRefrigerator") @Expose  val isRefrigerator: Boolean,
    @SerializedName("isShowerCubicle") @Expose  val isShowerCubicle: Boolean,
    @SerializedName("isTV") @Expose  val isTV: Boolean,
    @SerializedName("isWashingMachine") @Expose  val isWashingMachine: Boolean,
)