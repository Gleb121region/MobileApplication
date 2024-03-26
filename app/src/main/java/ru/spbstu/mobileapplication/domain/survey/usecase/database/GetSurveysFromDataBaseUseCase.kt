package ru.spbstu.mobileapplication.domain.survey.usecase.database

import android.util.Log
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey.repository.SurveyAnswersRepository
import javax.inject.Inject

class GetSurveysFromDataBaseUseCase @Inject constructor(
    private val surveyAnswersRepository: SurveyAnswersRepository
) {
    suspend operator fun invoke(): List<SurveyAnswersItem> {
        Log.d(TAG, "Get surveys")
        return surveyAnswersRepository.getAnswersFromDataBase()
    }

    private companion object {
        private const val TAG = "GetSurveysFromDataBaseUseCase"
    }
}