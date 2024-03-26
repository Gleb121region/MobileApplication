package ru.spbstu.mobileapplication.data.network.announcement

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementResponse
import ru.spbstu.mobileapplication.data.network.announcement.model.response.AnnouncementWithDescriptionResponse
import ru.spbstu.mobileapplication.domain.enums.City

interface AnnouncementApiService {

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/api/v1/announcement")
    suspend fun getFewAnnouncements(
        @Query("city") city: City,
        @Query("underground") underground: String?,
        @Query("district") district: String?,
        @Query(value = "apartmentTypes", encoded = true) apartmentTypes: List<String>?,
        @Query("maxPricePerMonth") maxPricePerMonth: Int?,
        @Query("minPricePerMonth") minPricePerMonth: Int?,
        @Query("maxArea") maxArea: Int?,
        @Query("minArea") minArea: Int?,
        @Query("isRefrigerator") isRefrigerator: Boolean?,
        @Query("isWashingMachine") isWashingMachine: Boolean?,
        @Query("isTV") isTV: Boolean?,
        @Query("isShowerCubicle") isShowerCubicle: Boolean?,
        @Query("isBathtub") isBathtub: Boolean?,
        @Query("isFurnitureRoom") isFurnitureRoom: Boolean?,
        @Query("isFurnitureKitchen") isFurnitureKitchen: Boolean?,
        @Query("isDishwasher") isDishwasher: Boolean?,
        @Query("isAirConditioning") isAirConditioning: Boolean?,
        @Query("isInternet") isInternet: Boolean?,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Header("Authorization") token: String
    ): List<AnnouncementResponse>

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/api/v1/announcement/{announcementId}")
    suspend fun getAnnouncementInfo(
        @Path("announcementId") flatId: Int,
        @Header("Authorization") token: String
    ): AnnouncementWithDescriptionResponse?

}