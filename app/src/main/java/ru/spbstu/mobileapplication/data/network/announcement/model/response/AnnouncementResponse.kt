package ru.spbstu.mobileapplication.data.network.announcement.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.ApartmentType

data class AnnouncementResponse(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("floor") @Expose val floor: Int,
    @SerializedName("floorsCount") @Expose val floorsCount: Int,
    @SerializedName("totalMeters") @Expose val totalMeters: Int,
    @SerializedName("apartmentType") @Expose val apartmentType: ApartmentType,
    @SerializedName("pricePerMonth") @Expose val pricePerMonth: Int,
    @SerializedName("address") @Expose val address: String,
    @SerializedName("underground") @Expose val underground: String,
    @SerializedName("photoUrls") @Expose val photoUrls: List<String>,
    @SerializedName("isLikedByUser") @Expose val isLikedByUser: Boolean,
)
