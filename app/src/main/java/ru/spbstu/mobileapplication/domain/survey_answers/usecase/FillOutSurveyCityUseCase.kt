package ru.spbstu.mobileapplication.domain.survey_answers.usecase

import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class FillOutSurveyCityUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun execute(city: City) {
        surveyAnswersRepository.fillOutSurveyCity(city)
    }
}
