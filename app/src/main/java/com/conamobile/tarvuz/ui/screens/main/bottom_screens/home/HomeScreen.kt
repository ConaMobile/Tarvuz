package com.conamobile.tarvuz.ui.screens.main.bottom_screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.items.LazyItem
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.home.items.itemsC
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import com.conamobile.tarvuz.ui.screens.main.mvvm.vm.MainViewModel
import com.conamobile.tarvuz.ui.theme.DefaultGreen
import com.conamobile.tarvuz.util.custom.ParentView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val scope = rememberCoroutineScope()
    val postsList = viewModel.postsList.asStateFlow()
    val itemsList = remember { mutableStateListOf<Posts>() }
    val lazyState = rememberLazyGridState()
    val postsPager = viewModel.postsPager.collectAsLazyPagingItems()
    val scrollToTopLazy by remember {
        derivedStateOf {
            lazyState.firstVisibleItemIndex > 0
        }
    }

    LaunchedEffect(true) {
        scope.launch {
            delay(50)
            lazyState.scrollToItem(viewModel.index)
            postsList.collect {
                itemsList.addAll(it)
            }
        }
    }
    DisposableEffect(key1 = LocalLifecycleOwner.current) {
        onDispose {
            viewModel.index = lazyState.firstVisibleItemIndex
        }
    }
    ParentView {
        LazyVerticalGrid(contentPadding = PaddingValues(top = 50.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            state = lazyState,
            columns = GridCells.Adaptive(minSize = 180.dp)) {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Card(Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        scope.launch {
                            lazyState.scrollToItem(index = 20)
                        }
                    }, backgroundColor = Color.DarkGray) {
                    Text(text = "Header")
                }
            }
            when (val state = postsPager.loadState.prepend) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    loading()
                }
                is LoadState.Error -> {
                    error(message = state.error.message ?: "")
                }
            }

            when (val state = postsPager.loadState.refresh) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    loading()
                }
                is LoadState.Error -> {
                    error(message = state.error.message ?: "")
                }
            }

            itemsC(items = postsPager) { item ->
                item?.let {
                    LazyItem(posts = Posts(photo = item.picture,
                        name = item.firstName,
                        price = item.title,
                        time = item.title,
                        location = item.title,
                        condition = false))
                }
            }

            when (val state = postsPager.loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> {
                    loading()
                }
                is LoadState.Error -> {
                    error(message = state.error.message ?: "")
                }
            }
        }
    }
}

private fun LazyGridScope.loading() {
    item(span = {
        GridItemSpan(maxLineSpan)
    }) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier
                .width(50.dp)
                .height(50.dp), color = DefaultGreen, strokeWidth = 8.dp)
        }
    }
}

private fun LazyGridScope.error(
    message: String,
) {
    item {
        Text(text = message,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.error)
    }
}