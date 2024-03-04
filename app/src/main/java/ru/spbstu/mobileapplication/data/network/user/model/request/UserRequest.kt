package ru.spbstu.mobileapplication.data.network.user.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.data.enums.Gender
import java.util.Date


data class UserUpdateRequest(
    @SerializedName("FirstName") @Expose val firstName: String?,
    @SerializedName("LastName") @Expose val lastName: String?,
    @SerializedName("About") @Expose val about: String?,
    @SerializedName("Gender") @Expose val gender: Gender?,
    @SerializedName("Age") @Expose val age: Int?,
    @SerializedName("BirthdayDate") @Expose val birthdayDate: Date?,
    @SerializedName("Mobile") @Expose val mobile: String?,
    @SerializedName("Email") @Expose val email: String?,
    @SerializedName("LinkVK") @Expose val linkVK: String?
)
