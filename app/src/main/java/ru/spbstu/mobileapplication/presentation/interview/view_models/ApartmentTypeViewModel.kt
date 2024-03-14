package ru.spbstu.mobileapplication.presentation.interview.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.survey_answers.usecase.FillOutSurveyApartmentTypeUseCase
import javax.inject.Inject

class ApartmentTypeViewModel @Inject constructor(
    private val fillOutSurveyApartmentTypeUseCase: FillOutSurveyApartmentTypeUseCase
) : ViewModel() {
    suspend fun chooseApartmentType(apartmentType: Set<ApartmentType>) {
        fillOutSurveyApartmentTypeUseCase.execute(apartmentType)
    }
}