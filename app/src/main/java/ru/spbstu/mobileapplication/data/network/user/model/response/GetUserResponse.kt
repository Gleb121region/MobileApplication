package ru.spbstu.mobileapplication.data.network.user.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.Gender
import java.util.Date


data class GetUserResponse(
    @SerializedName("firstname") @Expose val firstname: String? = null,
    @SerializedName("lastname") @Expose val lastname: String? = null,
    @SerializedName("about") @Expose val about: String? = null,
    @SerializedName("gender") @Expose val gender: Gender? = null,
    @SerializedName("birthdayDate") @Expose val birthdayDate: Date? = null,
    @SerializedName("phone") @Expose val phone: String? = null,
    @SerializedName("email") @Expose val email: String? = null,
    @SerializedName("photosUrl") @Expose val photos: Set<String>? = null
)