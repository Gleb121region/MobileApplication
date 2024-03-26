//package ru.spbstu.mobileapplication.domain.announcement.usecases
//
//import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
//import ru.spbstu.mobileapplication.domain.announcement.repository.AnnouncementRepository
//import javax.inject.Inject
//
//class AddAnnouncementItemUseCase @Inject constructor(
//    private val announcementRepository: AnnouncementRepository
//) {
//
//    suspend fun addAnnouncementItem(shopItem: AnnouncementEntity) {
//        announcementRepository.addAnnouncementItem(shopItem)
//    }
//}