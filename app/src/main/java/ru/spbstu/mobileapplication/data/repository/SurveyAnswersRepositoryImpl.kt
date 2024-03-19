package ru.spbstu.mobileapplication.data.repository

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.data.database.answer.AnswerInfoDao
import ru.spbstu.mobileapplication.data.mapper.SurveyMapper
import ru.spbstu.mobileapplication.data.network.survey.SurveyApiService
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey.entity.SurveyResult
import ru.spbstu.mobileapplication.domain.survey.repository.SurveyAnswersRepository
import javax.inject.Inject

class SurveyAnswersRepositoryImpl @Inject constructor(
    private val api: SurveyApiService,
    private val mapper: SurveyMapper,
    private val answerDao: AnswerInfoDao,
) : SurveyAnswersRepository {
    // network
    override suspend fun fillOutSurvey(survey: SurveyResult, token: String) {
        val requestDTO = mapper.mapSurveyResultToCreateSurveyRequest(survey)
        api.createSurvey(token, requestDTO)
    }

    override suspend fun getFillOutSurvey(token: String): Set<SurveyAnswersItem> {
        val responseDTO = api.getSurvey(token)
        val result = responseDTO.map { mapper.mapGetSurveyResponseToSurveyAnswersItem(it) }.toSet()
        result
            .map { mapper.mapSurveyAnswersItemToAnswerDbModel(it) }
            .map { answerDao.insertAnswer(it) }
        return result
    }

    // database
    override suspend fun insertAnswersIntoDataBase(survey: SurveyResult) {
        val answerDbModel = mapper.mapSurveyResultToAnswerDbModel(survey)
        answerDao.insertAnswer(answerDbModel)
    }

    override suspend fun getAnswersFromDataBase(): List<SurveyAnswersItem> =
        answerDao.getAnswers().map { mapper.mapAnswerDbModelToSurveyResult(it) }

    override suspend fun getLastAnswerFromDataBase(): AnswerDbModel =
        answerDao.findLastAnswer()

}
