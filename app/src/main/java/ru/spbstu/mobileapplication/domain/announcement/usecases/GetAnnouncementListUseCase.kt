package ru.spbstu.mobileapplication.domain.announcement.usecases

import ru.spbstu.mobileapplication.data.database.answer.AnswerDbModel
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
import javax.inject.Inject

class GetAnnouncementListUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {

    suspend operator fun invoke(
        model: AnswerDbModel, offset: Int = 0, token: String
    ): List<AnnouncementEntity> {
        return announcementRepository.getAnnouncementList(
            model, LIMIT, offset, token
        )
    }

    companion object {
        private const val LIMIT = 10
    }
}