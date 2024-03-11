package ru.spbstu.mobileapplication.presentation.interview.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.enums.interview.Term
import ru.spbstu.mobileapplication.domain.survey_answers.usecase.FillOutSurveyTermUseCase
import javax.inject.Inject

class TermViewModel @Inject constructor(
    private val fillOutSurveyTermUseCase: FillOutSurveyTermUseCase
) : ViewModel() {
    suspend fun chooseApartmentType(term: Term) {
        fillOutSurveyTermUseCase.execute(term)
    }
}