package ru.spbstu.mobileapplication.data.network.user.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.Gender
import java.util.Date


data class UpdateUserRequest(
    @SerializedName("firstname") @Expose val firstName: String?,
    @SerializedName("lastname") @Expose val lastName: String?,
    @SerializedName("about") @Expose val about: String?,
    @SerializedName("gender") @Expose val gender: Gender?,
    @SerializedName("birthdayDate") @Expose val birthdayDate: Date?,
    @SerializedName("phone") @Expose val phone: String?
)
