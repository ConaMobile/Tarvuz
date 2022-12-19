package com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.DummyList
import com.conamobile.tarvuz.ui.screens.main.mvvm.repository.MainRepository

class PostsPagingSource(
    private val repository: MainRepository,
) : PagingSource<Int, DummyList>() {

    override fun getRefreshKey(state: PagingState<Int, DummyList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DummyList> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = repository.loadPostsDummy(nextPageNumber, 10)
            LoadResult.Page(
                data = response.users,
                prevKey = null,
                nextKey = if (response.users.isNotEmpty()) response.page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
