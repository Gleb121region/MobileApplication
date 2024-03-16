package ru.spbstu.mobileapplication.presentation.interview.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyResult
import ru.spbstu.mobileapplication.domain.survey.usecase.database.InsertSurveyIntoDataBaseUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.network.FillOutSurveyUseCase
import javax.inject.Inject

class PurposeViewModel @Inject constructor(
    private val insertIntoDatabase: InsertSurveyIntoDataBaseUseCase,
    private val fillOutSurveyUseCase: FillOutSurveyUseCase,
) : ViewModel() {
    // todo: менять роль в зависимости от ответа, чтобы можно было создавать объявления.

    suspend fun recordIntoDB(survey: SurveyResult) {
        insertIntoDatabase(survey)
    }

    suspend fun sendRequest(survey: SurveyResult) {
        fillOutSurveyUseCase.fillOutSurvey(survey)
    }
}