package ru.spbstu.mobileapplication.presentation.interview.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.survey_answers.usecase.FillOutSurveyCityUseCase
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val fillOutSurveyCityUseCase: FillOutSurveyCityUseCase
) : ViewModel() {
    suspend fun chooseApartmentType(city: City) {
        fillOutSurveyCityUseCase.execute(city)
    }
}