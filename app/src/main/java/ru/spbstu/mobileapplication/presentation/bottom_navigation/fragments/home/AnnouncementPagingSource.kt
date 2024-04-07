package ru.spbstu.mobileapplication.presentation.bottom_navigation.fragments.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.spbstu.mobileapplication.domain.announcement.entity.AnnouncementEntity
import ru.spbstu.mobileapplication.domain.authentication.usecase.local_storage.GetTokenFromLocalStorageUseCase

class AnnouncementPagingSource(
    private val viewModel: HomeViewModel,
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
) : PagingSource<Int, AnnouncementEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnnouncementEntity> {
        return try {
            val token = "Bearer ${getTokenFromLocalStorageUseCase().accessToken}"
            val lastSurvey = viewModel.getLastSurveyFromDB()
            val limit = params.loadSize
            val offset = params.key ?: 0
            val announcements = viewModel.sendRequest(lastSurvey, limit, offset, token)
            LoadResult.Page(
                data = announcements,
                prevKey = if (offset == 0) null else offset - 1,
                nextKey = if (announcements.isEmpty()) null else offset + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (httpE: HttpException) {
            LoadResult.Error(httpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnnouncementEntity>): Int? {
        return null
    }
}