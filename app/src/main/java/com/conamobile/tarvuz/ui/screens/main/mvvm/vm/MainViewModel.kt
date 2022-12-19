package com.conamobile.tarvuz.ui.screens.main.mvvm.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.pagination.PostsPagingSource
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import com.conamobile.tarvuz.ui.screens.main.mvvm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    val postsList = MutableStateFlow<List<Posts>>(emptyList())
    var index: Int by mutableStateOf(0)

    val postsPager = Pager(PagingConfig(pageSize = 20)) {
        PostsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}