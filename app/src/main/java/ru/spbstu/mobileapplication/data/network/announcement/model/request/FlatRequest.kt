package ru.spbstu.mobileapplication.data.network.announcement.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlatRequest(
    @SerializedName("City") @Expose val city: String,
    @SerializedName("Underground") @Expose val underground: String?,
    @SerializedName("District") @Expose val district: String?,
    @SerializedName("RoomsCounts") @Expose val roomsCounts: Set<Int>?,
    @SerializedName("MaxPricePerMonth") @Expose val maxPricePerMonth: Double?,
    @SerializedName("MinPricePerMonth") @Expose val minPricePerMonth: Double?,
    @SerializedName("IsRefrigerator") @Expose val isRefrigerator: Boolean?,
    @SerializedName("IsWashingMachine") @Expose val isWashingMachine: Boolean?,
    @SerializedName("IsTV") @Expose val isTV: Boolean?,
    @SerializedName("IsShowerCubicle") @Expose val isShowerCubicle: Boolean?,
    @SerializedName("IsBathtub") @Expose val isBathtub: Boolean?,
    @SerializedName("IsFurnitureRoom") @Expose val isFurnitureRoom: Boolean?,
    @SerializedName("IsFurnitureKitchen") @Expose val isFurnitureKitchen: Boolean?,
    @SerializedName("IsDishwasher") @Expose val isDishwasher: Boolean?,
    @SerializedName("IsAirConditioning") @Expose val isAirConditioning: Boolean?,
    @SerializedName("IsInternet") @Expose val isInternet: Boolean?
)
