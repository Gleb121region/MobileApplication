package ru.spbstu.mobileapplication.data.network.user

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import ru.spbstu.mobileapplication.data.network.user.model.request.UpdateUserRequest
import ru.spbstu.mobileapplication.data.network.user.model.response.GetUserResponse

interface UserApiService {
    @Headers("accept: */*")
    @GET("api/v1/users")
    suspend fun getInfoAboutUser(@Header("Authorization") token: String): GetUserResponse

    @Headers("accept: */*", "Content-Type: application/json")
    @PUT("/api/v1/users")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String, @Body request: UpdateUserRequest
    )


    @Headers("accept: */*")
    @DELETE("/api/v1/users")
    suspend fun deleteUser(@Header("Authorization") token: String)
}