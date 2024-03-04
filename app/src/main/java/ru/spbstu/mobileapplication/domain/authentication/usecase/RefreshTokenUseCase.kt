//package ru.spbstu.mobileapplication.domain.authentication.usecase
//
//import ru.spbstu.mobileapplication.domain.authentication.entity.RefreshItem
//import ru.spbstu.mobileapplication.domain.authentication.entity.TokenItem
//import ru.spbstu.mobileapplication.domain.authentication.repository.AuthRepository
//import javax.inject.Inject
//
//class RefreshTokenUseCase @Inject constructor(
//    private val authRepository: AuthRepository
//) {
//    suspend fun restoreAccess(restoreItem: RefreshItem): TokenItem {
//        return authRepository.refresh(restoreItem)
//    }
//}