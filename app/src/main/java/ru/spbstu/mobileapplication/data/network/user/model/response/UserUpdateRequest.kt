package ru.spbstu.mobileapplication.data.network.user.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.Gender


data class UserUpdateRequest(
    @SerializedName("firstname") @Expose val firstname: String? = null,
    @SerializedName("lastname") @Expose val lastname: String? = null,
    @SerializedName("about") @Expose val about: String? = null,
    @SerializedName("gender") @Expose val gender: Gender? = null,
    @SerializedName("birthdayDate") @Expose val birthdayDate: String? = null,
    @SerializedName("phone") @Expose val phone: String? = null,
    @SerializedName("linkVK") @Expose val linkVK: String? = null,
    @SerializedName("email") @Expose val email: String? = null
)