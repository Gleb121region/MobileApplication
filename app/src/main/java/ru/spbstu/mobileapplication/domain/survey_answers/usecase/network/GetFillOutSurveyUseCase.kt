package ru.spbstu.mobileapplication.domain.survey_answers.usecase.network

import android.util.Log
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class GetFillOutSurveyUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun getFillOutSurvey(): Set<SurveyAnswersItem> {
        Log.d(TAG, "get fill out survey")
        return surveyAnswersRepository.getFillOutSurvey()
    }

    private companion object {
        private const val TAG = "GetFillOutSurveyUseCase"
    }
}