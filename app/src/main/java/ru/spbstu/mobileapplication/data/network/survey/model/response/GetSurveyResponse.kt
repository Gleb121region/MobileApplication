package ru.spbstu.mobileapplication.data.network.survey.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.enums.interview.Term

data class GetSurveyResponse(
    @SerializedName("apartmentType") @Expose val apartmentType: Set<ApartmentType>,
    @SerializedName("city") @Expose val city: City,
    @SerializedName("term") @Expose val term: Term,
    @SerializedName("maxArea") @Expose val maxArea: Int,
    @SerializedName("maxBudget") @Expose val maxBudget: Int,
    @SerializedName("minArea") @Expose val minArea: Int,
    @SerializedName("minBudget") @Expose val minBudget: Int,
)