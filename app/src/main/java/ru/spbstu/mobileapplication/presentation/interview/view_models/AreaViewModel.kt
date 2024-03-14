package ru.spbstu.mobileapplication.presentation.interview.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.survey_answers.usecase.FillOutSurveyAreaUseCase
import javax.inject.Inject

class AreaViewModel @Inject constructor(
    private val fillOutSurveyAreaUseCase: FillOutSurveyAreaUseCase
) : ViewModel() {
    suspend fun chooseArea(minArea: Int, maxArea: Int) {
        fillOutSurveyAreaUseCase.execute(minArea, maxArea)
    }
}