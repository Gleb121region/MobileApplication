package ru.spbstu.mobileapplication.domain.survey_answers.usecase

import ru.spbstu.mobileapplication.domain.enums.interview.Term
import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class FillOutSurveyTermUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun execute(term: Term) {
        surveyAnswersRepository.fillOutSurveyTerm(term)
    }
}
