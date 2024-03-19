package ru.spbstu.mobileapplication.domain.survey.usecase.network

import android.util.Log
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey.repository.SurveyAnswersRepository
import javax.inject.Inject

class GetFillOutSurveyUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend fun getFillOutSurvey(token: String): Set<SurveyAnswersItem> {
        Log.d(TAG, "get fill out survey")
        return surveyAnswersRepository.getFillOutSurvey(token)
    }

    private companion object {
        private const val TAG = "GetFillOutSurveyUseCase"
    }
}