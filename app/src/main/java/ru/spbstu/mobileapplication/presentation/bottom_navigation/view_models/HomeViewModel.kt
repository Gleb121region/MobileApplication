package ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models

import androidx.lifecycle.ViewModel
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementListUseCase
import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.database.GetLastSurveyFromDataBaseUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenFromLocalStorageUseCase,
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase : GetAnnouncementListUseCase
) : ViewModel() {

    fun getToken(): TokenItem = getTokenUseCase()

    suspend fun getLastSurvey() = getLastSurveyFromDataBaseUseCase()

// todo:      SurveyAnswersItem TO AnnouncementFilterEntity

//    val  x = getAnnouncementListUseCase.invoke(,10,0)

//    suspend fun getAnnouncementByParam()
}