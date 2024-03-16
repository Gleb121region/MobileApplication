package ru.spbstu.mobileapplication.domain.survey.usecase.database

import android.util.Log
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey.repository.SurveyAnswersRepository
import javax.inject.Inject

class GetLastSurveyFromDataBaseUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend operator fun invoke(): SurveyAnswersItem {
        Log.d(TAG, "Get survey")
        return surveyAnswersRepository.getLastAnswerFromDataBase()
    }

    private companion object {
        private const val TAG = "GetLastSurveyFromDataBaseUseCase"
    }
}