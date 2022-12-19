package com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import com.conamobile.tarvuz.ui.screens.main.mvvm.repository.MainRepository

class PostsPagingSource(
    private val repository: MainRepository,
) : PagingSource<Int, Posts>() {

    override fun getRefreshKey(state: PagingState<Int, Posts>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Posts> = try {
        val page = params.key ?: 1
        val data = repository.loadPosts()
        val dataItem = ArrayList<Posts>()
        data.collect {
            if (it.isSuccessful) dataItem.addAll(it.body() ?: emptyList())
        }
        LoadResult.Page(data = dataItem,
            prevKey = null,
            nextKey = if (dataItem.isEmpty()) null else page + 1)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}