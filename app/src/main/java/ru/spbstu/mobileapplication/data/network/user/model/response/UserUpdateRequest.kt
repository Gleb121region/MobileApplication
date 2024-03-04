package ru.spbstu.mobileapplication.data.network.user.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.data.enums.Gender


data class UserUpdateRequest(
    @SerializedName("Firstname") @Expose val firstname: String? = null,
    @SerializedName("Lastname") @Expose val lastname: String? = null,
    @SerializedName("About") @Expose val about: String? = null,
    @SerializedName("Gender") @Expose val gender: Gender? = null,
    @SerializedName("BirthdayDate") @Expose val birthdayDate: String? = null,
    @SerializedName("Phone") @Expose val phone: String? = null,
    @SerializedName("LinkVK") @Expose val linkVK: String? = null,
    @SerializedName("Email") @Expose val email: String? = null
)