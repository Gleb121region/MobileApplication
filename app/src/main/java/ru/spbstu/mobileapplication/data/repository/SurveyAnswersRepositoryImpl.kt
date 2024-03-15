package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.database.answer.AnswerInfoDao
import ru.spbstu.mobileapplication.data.mapper.SurveyMapper
import ru.spbstu.mobileapplication.data.network.survey.SurveyApiService
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyResult
import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class SurveyAnswersRepositoryImpl @Inject constructor(
    private val api: SurveyApiService,
    private val mapper: SurveyMapper,
    private val answerDao: AnswerInfoDao,
    // todo: исправить.
    private val getToken: GetTokenFromLocalStorageUseCase
) : SurveyAnswersRepository {
    // network
    override suspend fun fillOutSurvey(survey: SurveyResult) {
        val requestDTO = mapper.mapSurveyResultToCreateSurveyRequest(survey)
        val token = getAccessToken()
        api.createSurvey(token, requestDTO)
    }

    override suspend fun getFillOutSurvey(): Set<SurveyAnswersItem> {
        val token = getAccessToken()
        val responseDTO = api.getSurvey(token)
        val result = responseDTO.map { mapper.mapGetSurveyResponseToSurveyAnswersItem(it) }.toSet()
        result
            .map { mapper.mapSurveyAnswersItemToAnswerDbModel(it) }
            .map { answerDao.insertAnswer(it) }
        return result
    }

    private fun getAccessToken(): String {
        return "Bearer ${getToken().accessToken}"
    }

    // database
    override suspend fun insertAnswersIntoDataBase(survey: SurveyResult) {
        val answerDbModel = mapper.mapSurveyResultToAnswerDbModel(survey)
        answerDao.insertAnswer(answerDbModel)
    }

    override suspend fun getAnswersFromDataBase(): List<SurveyAnswersItem> =
        answerDao.getAnswers().map { mapper.mapAnswerDbModelToSurveyResult(it) }

    override suspend fun getLastAnswerFromDataBase(): SurveyAnswersItem =
        mapper.mapAnswerDbModelToSurveyResult(
            answerDao.findLastAnswer() ?: throw RuntimeException("answerDbModel is null")
        )

}
