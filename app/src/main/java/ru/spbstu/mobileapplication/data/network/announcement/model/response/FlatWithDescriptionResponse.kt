package ru.spbstu.mobileapplication.data.network.announcement.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlatWithDescriptionResponse(
    @SerializedName("Id") @Expose val id: Int,
    @SerializedName("Floor") @Expose val floor: Int,
    @SerializedName("FloorsCount") @Expose val floorsCount: Int,
    @SerializedName("TotalMeters") @Expose val totalMeters: Double,
    @SerializedName("RoomsCount") @Expose val roomsCount: Int,
    @SerializedName("PricePerMonth") @Expose val pricePerMonth: Double,
    @SerializedName("Address") @Expose val address: String,
    @SerializedName("Underground") @Expose val underground: String,
    @SerializedName("PhotoUrls") @Expose val photoUrls: Set<String>,
    @SerializedName("Description") @Expose val description: String
)
