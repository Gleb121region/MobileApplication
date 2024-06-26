package ru.spbstu.mobileapplication.data.network.announcement.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.City

data class AnnouncementFilterRequest(
    @SerializedName("city") @Expose val city: City,
    @SerializedName("underground") @Expose val underground: String?,
    @SerializedName("district") @Expose val district: String?,
    @SerializedName("apartmentTypes") @Expose val apartmentTypes: Set<ApartmentType>?,
    @SerializedName("maxPricePerMonth") @Expose val maxPricePerMonth: Int?,
    @SerializedName("minPricePerMonth") @Expose val minPricePerMonth: Int?,
    @SerializedName("maxArea") @Expose val maxArea: Int?,
    @SerializedName("minArea") @Expose val minArea: Int?,
    @SerializedName("isRefrigerator") @Expose val isRefrigerator: Boolean?,
    @SerializedName("isWashingMachine") @Expose val isWashingMachine: Boolean?,
    @SerializedName("isTV") @Expose val isTV: Boolean?,
    @SerializedName("isShowerCubicle") @Expose val isShowerCubicle: Boolean?,
    @SerializedName("isBathtub") @Expose val isBathtub: Boolean?,
    @SerializedName("isFurnitureRoom") @Expose val isFurnitureRoom: Boolean?,
    @SerializedName("isFurnitureKitchen") @Expose val isFurnitureKitchen: Boolean?,
    @SerializedName("isDishwasher") @Expose val isDishwasher: Boolean?,
    @SerializedName("isAirConditioning") @Expose val isAirConditioning: Boolean?,
    @SerializedName("isInternet") @Expose val isInternet: Boolean?
)
