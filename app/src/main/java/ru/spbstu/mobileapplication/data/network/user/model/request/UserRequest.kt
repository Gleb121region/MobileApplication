package ru.spbstu.mobileapplication.data.network.user.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.data.enums.Gender
import java.util.Date


data class UserUpdateRequest(
    @SerializedName("firstName") @Expose val firstName: String?,
    @SerializedName("lastName") @Expose val lastName: String?,
    @SerializedName("about") @Expose val about: String?,
    @SerializedName("gender") @Expose val gender: Gender?,
    @SerializedName("age") @Expose val age: Int?,
    @SerializedName("birthdayDate") @Expose val birthdayDate: Date?,
    @SerializedName("mobile") @Expose val mobile: String?,
    @SerializedName("email") @Expose val email: String?,
    @SerializedName("linkVK") @Expose val linkVK: String?
)
