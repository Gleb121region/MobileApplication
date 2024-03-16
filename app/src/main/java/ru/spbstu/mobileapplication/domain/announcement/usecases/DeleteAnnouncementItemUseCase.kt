//package ru.spbstu.mobileapplication.domain.announcement.usecases
//
//import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
//import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
//import javax.inject.Inject
//
//class DeleteAnnouncementItemUseCase @Inject constructor(
//    private val announcementRepository: AnnouncementRepository
//) {
//
//    suspend fun deleteAnnouncementItem(shopItem: AnnouncementEntity) {
//        announcementRepository.deleteAnnouncementItem(shopItem)
//    }
//}