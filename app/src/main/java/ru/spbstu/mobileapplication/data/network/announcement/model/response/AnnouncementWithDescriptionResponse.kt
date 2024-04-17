package ru.spbstu.mobileapplication.data.network.announcement.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.ApartmentType

data class AnnouncementWithDescriptionResponse(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("city") @Expose val city: String = "",
    @SerializedName("floor") @Expose val floor: Int,
    @SerializedName("floorsCount") @Expose val floorsCount: Int,
    @SerializedName("totalMeters") @Expose val totalMeters: Int,
    @SerializedName("apartmentType") @Expose val apartmentType: ApartmentType,
    @SerializedName("pricePerMonth") @Expose val pricePerMonth: Int,
    @SerializedName("address") @Expose val address: String,
    @SerializedName("underground") @Expose val underground: String,
    @SerializedName("photoUrls") @Expose val photoUrls: List<String>,
    @SerializedName("isLikedByUser") @Expose val isLikedByUser: Boolean,
    @SerializedName("description") @Expose val description: String,
    @SerializedName("isRefrigerator") @Expose val isRefrigerator: Boolean = false,
    @SerializedName("isWashingMachine") @Expose val isWashingMachine: Boolean = false,
    @SerializedName("isTV") @Expose val isTV: Boolean = false,
    @SerializedName("isShowerCubicle") @Expose val isShowerCubicle: Boolean = false,
    @SerializedName("isBathtub") @Expose val isBathtub: Boolean = false,
    @SerializedName("isFurnitureRoom") @Expose val isFurnitureRoom: Boolean = false,
    @SerializedName("isFurnitureKitchen") @Expose val isFurnitureKitchen: Boolean = false,
    @SerializedName("isDishwasher") @Expose val isDishwasher: Boolean = false,
    @SerializedName("isAirConditioning") @Expose val isAirConditioning: Boolean = false,
    @SerializedName("isInternet") @Expose val isInternet: Boolean = false,
    @SerializedName("isHide") @Expose val isHide: Boolean = false
)
