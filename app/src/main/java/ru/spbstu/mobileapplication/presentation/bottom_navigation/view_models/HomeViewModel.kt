package ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementListUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.database.GetLastSurveyFromDataBaseUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase: GetAnnouncementListUseCase,
) : ViewModel() {

    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        return getLastSurveyFromDataBaseUseCase()
    }

    suspend fun sendRequest(
        lastSurvey: AnswerDbModel, limit: Int = 10, offset: Int = 0, token: String
    ): List<AnnouncementEntity> {
        return getAnnouncementListUseCase(lastSurvey, limit, offset, token)
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
