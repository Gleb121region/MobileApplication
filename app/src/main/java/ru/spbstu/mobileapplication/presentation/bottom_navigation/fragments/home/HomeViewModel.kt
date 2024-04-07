package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementListUseCase
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.database.GetLastSurveyFromDataBaseUseCase
import ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home.AnnouncementPagingSource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase: GetAnnouncementListUseCase,
    private val createFeedbackUseCase: CreateFeedbackUseCase,
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    val announcementList = Pager(PagingConfig(1)) {
        AnnouncementPagingSource(this, getTokenFromLocalStorageUseCase)
    }.flow.cachedIn(viewModelScope)

    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        return getLastSurveyFromDataBaseUseCase()
    }

    suspend fun sendRequest(
        lastSurvey: AnswerDbModel, limit: Int = 10, offset: Int = 0, token: String
    ): List<AnnouncementEntity> {
        return getAnnouncementListUseCase(lastSurvey, limit, offset, token)
    }

    suspend fun sendRequest(
        feedbackCreateEntity: FeedbackCreateEntity, token: String
    ) {
        createFeedbackUseCase(feedbackCreateEntity, token)
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
