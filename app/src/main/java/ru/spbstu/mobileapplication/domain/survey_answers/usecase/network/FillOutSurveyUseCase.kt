package ru.spbstu.mobileapplication.domain.survey_answers.usecase.network

import android.util.Log
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult
import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class FillOutSurveyUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun fillOutSurvey(survey: SurveyResult) {
        Log.d(TAG, "fill out survey")
        return surveyAnswersRepository.fillOutSurvey(survey)
    }

    private companion object {
        private const val TAG = "FillOutSurveyUseCase"
    }
}