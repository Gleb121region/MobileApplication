package ru.spbstu.mobileapplication.presentation.interview.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult
import ru.spbstu.mobileapplication.domain.survey_answers.usecase.database.InsertSurveyIntoDataBaseUseCase
import ru.spbstu.mobileapplication.domain.survey_answers.usecase.network.FillOutSurveyUseCase
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val insertIntoDatabase: InsertSurveyIntoDataBaseUseCase,
    private val fillOutSurveyUseCase: FillOutSurveyUseCase,
) : ViewModel() {

    suspend fun recordIntoDB(survey: SurveyResult) {
        insertIntoDatabase(survey)
    }

    suspend fun sendRequest(survey: SurveyResult){
        fillOutSurveyUseCase.fillOutSurvey(survey)
    }

}