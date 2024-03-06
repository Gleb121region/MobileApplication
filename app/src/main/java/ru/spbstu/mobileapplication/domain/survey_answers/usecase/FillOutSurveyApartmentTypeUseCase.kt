package ru.spbstu.mobileapplication.domain.survey_answers.usecase

import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class FillOutSurveyApartmentTypeUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun execute(apartmentType: Set<ApartmentType>) {
        surveyAnswersRepository.fillOutSurveyApartmentType(apartmentType)
    }
}