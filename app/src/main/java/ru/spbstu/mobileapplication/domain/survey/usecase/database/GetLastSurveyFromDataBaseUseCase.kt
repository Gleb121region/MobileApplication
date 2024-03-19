package ru.spbstu.mobileapplication.domain.survey.usecase.database

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.survey.repository.SurveyAnswersRepository
import javax.inject.Inject

class GetLastSurveyFromDataBaseUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend operator fun invoke(): AnswerDbModel =
        surveyAnswersRepository.getLastAnswerFromDataBase()


    private companion object {
        private const val TAG = "GetLastSurveyFromDataBaseUseCase"
    }
}