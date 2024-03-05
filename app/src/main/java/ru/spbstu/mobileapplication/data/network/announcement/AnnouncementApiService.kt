package ru.spbstu.mobileapplication.data.network.announcement

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.spbstu.mobileapplication.data.network.announcement.model.response.FlatResponse
import ru.spbstu.mobileapplication.data.network.announcement.model.response.FlatWithDescriptionResponse

interface AnnouncementApiService {

    @Headers("accept: */*")
    @GET("/api/v1/flats")
    suspend fun getFewFlats(
        @Query("city") city: String,
        @Query("underground") underground: String?,
        @Query("district") district: String?,
        @Query("roomsCounts") roomsCounts: List<Int>?,
        @Query("maxPricePerMonth") maxPricePerMonth: Double?,
        @Query("minPricePerMonth") minPricePerMonth: Double?,
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
    ): List<FlatResponse>

    @Headers("accept: */*")
    @GET("/api/v1/flats/{flatId}")
    suspend fun getFlatInfo(
        @Path("flatId") flatId: Int,
        @Header("Authorization") token: String
    ): FlatWithDescriptionResponse?

}