package ru.spbstu.mobileapplication.domain.survey.usecase.database

import android.util.Log
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyResult
import ru.spbstu.mobileapplication.domain.survey.repository.SurveyAnswersRepository
import javax.inject.Inject

class InsertSurveyIntoDataBaseUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend operator fun invoke(survey: SurveyResult) {
        Log.d(TAG, "insert survey into database")
        return surveyAnswersRepository.insertAnswersIntoDataBase(survey)
    }

    private companion object {
        private const val TAG = "InsertSurveyIntoDataBaseUseCase"
    }
}