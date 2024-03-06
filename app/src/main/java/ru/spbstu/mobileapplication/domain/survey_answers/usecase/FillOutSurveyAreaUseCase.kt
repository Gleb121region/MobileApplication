package ru.spbstu.mobileapplication.domain.survey_answers.usecase

import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class FillOutSurveyAreaUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun execute(minArea: Int, maxArea: Int) {
        surveyAnswersRepository.fillOutSurveyArea(minArea, maxArea)
    }
}