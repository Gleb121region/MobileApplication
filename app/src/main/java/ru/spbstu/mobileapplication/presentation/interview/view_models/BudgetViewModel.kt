package ru.spbstu.mobileapplication.presentation.interview.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.survey_answers.usecase.FillOutSurveyBudgetUseCase
import javax.inject.Inject

class BudgetViewModel @Inject constructor(
    private val fillOutSurveyBudgetUseCase: FillOutSurveyBudgetUseCase
) : ViewModel() {
    suspend fun chooseArea(minBudget: Int, maxBudget: Int) {
        fillOutSurveyBudgetUseCase.execute(minBudget, maxBudget)
    }
}