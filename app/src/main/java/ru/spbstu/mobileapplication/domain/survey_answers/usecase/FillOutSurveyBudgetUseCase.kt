package ru.spbstu.mobileapplication.domain.survey_answers.usecase

import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class FillOutSurveyBudgetUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun execute(minBudget: Int, maxBudget: Int) {
        surveyAnswersRepository.fillOutSurveyBudget(minBudget, maxBudget)
    }
}