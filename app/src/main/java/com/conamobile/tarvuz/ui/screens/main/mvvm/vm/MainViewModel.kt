package com.conamobile.tarvuz.ui.screens.main.mvvm.vm

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.pagination.PostsPagingSource
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.staggered.LazyVerticalGridWithHeaderAndFooterExampleDummyServer
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.staggered.ListItemData
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import com.conamobile.tarvuz.ui.screens.main.mvvm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

const val GRID_LIST_HEADER_COUNT = 1
const val GRID_LIST_FOOTER_COUNT = 1

class LazyVerticalGridWithHeaderAndFooterExampleViewModelState {
    val loading = mutableStateOf(false)
    fun toggleLoading(isLoading: Boolean) {
        loading.value = isLoading
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext application: Context,
) : ViewModel() {
    val postsList = MutableStateFlow<List<Posts>>(emptyList())

    val state = LazyVerticalGridWithHeaderAndFooterExampleViewModelState()
    val list = mutableStateListOf<ListItemData>()

    val postsPager: Flow<PagingData<Posts>> = Pager(
        pagingSourceFactory = { PostsPagingSource(repository) },
        config = PagingConfig(pageSize = 20)
    ).flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            loadPosts()
        }
    }

    private suspend fun loadPosts() {
        repository.loadPosts().collect {
            if (it.isSuccessful) {
                postsList.value = it.body() ?: emptyList()
            }
        }
    }

    fun fetchData(offset: Int, limit: Int) = viewModelScope.launch {

        Log.d("@@@", "fetchData $offset $limit")

        state.toggleLoading(true)

        try {
            coroutineScope {

                val fetch = async(Dispatchers.IO) {
                    delay(500)
                    LazyVerticalGridWithHeaderAndFooterExampleDummyServer.fetchDataFromDummyServer(
                        offset,
                        limit)
                }

                val tempList = fetch.await()

                state.toggleLoading(false)

                if (offset == 0) {
                    // reload
                    list.clear()

                    // add header
                    list.add(
                        ListItemData(type = ListItemData.Type.HEADER)
                    )
                }

                list.addAll(tempList)
            }
        } catch (e: Exception) {
            state.toggleLoading(false)
            e.printStackTrace()
        }
    }
}