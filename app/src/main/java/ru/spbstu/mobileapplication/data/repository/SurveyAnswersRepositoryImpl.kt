package ru.spbstu.mobileapplication.data.repository

import androidx.lifecycle.MutableLiveData
import ru.spbstu.mobileapplication.data.database.answer.AnswerInfoDao
import ru.spbstu.mobileapplication.data.database.token.TokenInfoDao
import ru.spbstu.mobileapplication.data.mapper.SurveyMapper
import ru.spbstu.mobileapplication.data.network.survey.SurveyApiService
import ru.spbstu.mobileapplication.domain.enums.interview.ApartmentType
import ru.spbstu.mobileapplication.domain.enums.interview.City
import ru.spbstu.mobileapplication.domain.enums.interview.Term
import ru.spbstu.mobileapplication.domain.survey_answers.entity.SurveyAnswersItem
import ru.spbstu.mobileapplication.domain.survey_answers.repository.SurveyAnswersRepository
import javax.inject.Inject

class SurveyAnswersRepositoryImpl @Inject constructor(
    private val api: SurveyApiService,
    private val mapper: SurveyMapper,
    private val answerDao: AnswerInfoDao,
    private val tokenInfoDao: TokenInfoDao
) : SurveyAnswersRepository {
    private val _tempSurvey =
        MutableLiveData(
            SurveyAnswersItem(
                Term.LONG,
                setOf<ApartmentType>(
                    ApartmentType.STUDIO,
                    ApartmentType.ONE_ROOM_APARTMENT,
                    ApartmentType.TWO_ROOM_APARTMENT
                ),
                City.MOSCOW,
                0, 100_000,
                0, 1_000_000
            )
        )

    // network
    override suspend fun fillOutSurvey(survey: SurveyAnswersItem) {
        val requestDTO = mapper.mapSurveyAnswersItemToCreateSurveyRequest(survey)
        val token = findToken()
        api.createSurvey(token, requestDTO)
        val answerDbModel = mapper.mapSurveyAnswersItemToAnswerDbModel(survey)
        answerDao.insertAnswer(answerDbModel)
    }

    override suspend fun getFillOutSurvey(): Set<SurveyAnswersItem> {
        val token = findToken()
        val responseDTO = api.getSurvey(token)
        val result = responseDTO.map { mapper.mapGetSurveyResponseToSurveyAnswersItem(it) }.toSet()
        result
            .map { mapper.mapSurveyAnswersItemToAnswerDbModel(it) }
            .map { answerDao.insertAnswer(it) }
        return result
    }

    private suspend fun findToken(): String {
        return tokenInfoDao.getLastToken()?.token ?: " "
    }

    // local
    // enums
    override suspend fun fillOutSurveyTerm(term: Term) {
        _tempSurvey.value = _tempSurvey.value?.copy(term = term)
    }

    override suspend fun fillOutSurveyApartmentType(apartmentType: Set<ApartmentType>) {
        _tempSurvey.value = _tempSurvey.value?.copy(apartmentType = apartmentType)
    }

    override suspend fun fillOutSurveyCity(city: City) {
        _tempSurvey.value = _tempSurvey.value?.copy(city = city)
    }

    // Int
    override suspend fun fillOutSurveyBudget(minBudget: Int, maxBudget: Int) {
        _tempSurvey.value = _tempSurvey.value?.copy(minBudget = minBudget, maxBudget = maxBudget)
    }

    override suspend fun fillOutSurveyArea(minArea: Int, maxArea: Int) {
        _tempSurvey.value = _tempSurvey.value?.copy(minArea = minArea, maxArea = maxArea)
    }
}
