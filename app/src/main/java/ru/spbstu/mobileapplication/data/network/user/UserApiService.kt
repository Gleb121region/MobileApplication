package ru.spbstu.mobileapplication.data.network.user

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import ru.spbstu.mobileapplication.data.network.user.model.response.UserUpdateRequest

interface UserApiService {
    @Headers("accept: */*")
    @GET("api/v1/users")
    fun getInfoAboutUser(@Header("Authorization") token: String): Call<UserUpdateRequest>

    @Headers("accept: */*", "Content-Type: application/json")
    @PUT("/api/v1/users")
    fun updateUserInfo(
        @Header("Authorization") token: String,
        @Body request: UserUpdateRequest
    ): Call<Void>


    @Headers("accept: */*")
    @DELETE("/api/v1/users")
    fun deleteUser(@Header("Authorization") token: String): Call<Void>
}