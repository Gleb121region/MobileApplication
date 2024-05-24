package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.usecases.GetAnnouncementListUseCase
import ru.spbstu.mobileapplication.domain.feedback.entity.FeedbackCreateEntity
import ru.spbstu.mobileapplication.domain.feedback.usecases.CreateFeedbackUseCase
import ru.spbstu.mobileapplication.domain.survey.usecase.database.GetLastSurveyFromDataBaseUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLastSurveyFromDataBaseUseCase: GetLastSurveyFromDataBaseUseCase,
    private val getAnnouncementListUseCase: GetAnnouncementListUseCase,
    private val createFeedbackUseCase: CreateFeedbackUseCase
) : ViewModel() {
    private val _intentFlow = MutableStateFlow<Intent?>(null)
    val intentFlow: StateFlow<Intent?> = _intentFlow

    val selectedAnnouncementId: MutableLiveData<Int> = MutableLiveData()

    val announcements: MutableLiveData<MutableList<AnnouncementEntity>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun getLastSurveyFromDB(): AnswerDbModel {
        Log.d(TAG, "getLastSurveyFromDB started")
        isLoading.postValue(true)
        val model: AnswerDbModel = getLastSurveyFromDataBaseUseCase()
        isLoading.postValue(false)
        return model
    }

    suspend fun getAnnouncements(
        lastSurvey: AnswerDbModel, offset: Int = 0, token: String
    ) {
        Log.d(TAG, "Is announcements empty? ${announcements.value?.isEmpty() ?: true}")
        try {
            isLoading.postValue(true)
            val announcementEntities =
                getAnnouncementListUseCase(lastSurvey, offset, token).toMutableList()
            if (offset == 0) {
                announcements.postValue(announcementEntities)
            } else {
                val currentList = announcements.value ?: mutableListOf()
                currentList.addAll(announcementEntities)
                announcements.postValue(currentList)
            }
            isLoading.postValue(false)
            Log.d(TAG, announcements.toString())
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }


    suspend fun createFeedback(feedbackCreateEntity: FeedbackCreateEntity, token: String) {
        Log.d(TAG, "createFeedback started")
        isLoading.postValue(true)
        createFeedbackUseCase(feedbackCreateEntity, token)
        isLoading.postValue(false)
        Log.d(TAG, "createFeedback finished")
    }

    fun sendCallIntent(phone: String) = viewModelScope.launch {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        _intentFlow.emit(intent)
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}
