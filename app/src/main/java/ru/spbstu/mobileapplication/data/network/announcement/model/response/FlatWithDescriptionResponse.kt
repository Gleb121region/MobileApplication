package ru.spbstu.mobileapplication.data.network.announcement.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlatWithDescriptionResponse(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("floor") @Expose val floor: Int,
    @SerializedName("floorsCount") @Expose val floorsCount: Int,
    @SerializedName("totalMeters") @Expose val totalMeters: Double,
    @SerializedName("roomsCount") @Expose val roomsCount: Int,
    @SerializedName("pricePerMonth") @Expose val pricePerMonth: Double,
    @SerializedName("address") @Expose val address: String,
    @SerializedName("underground") @Expose val underground: String,
    @SerializedName("photoUrls") @Expose val photoUrls: Set<String>,
    @SerializedName("description") @Expose val description: String
)
