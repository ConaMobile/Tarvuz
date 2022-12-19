package com.conamobile.tarvuz.ui.screens.main.bottom_screens.home

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.conamobile.tarvuz.ui.screens.main.bottom_screens.items.LazyItem
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import com.conamobile.tarvuz.ui.screens.main.mvvm.vm.MainViewModel
import com.conamobile.tarvuz.util.custom.ParentView
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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
//    val lastItemVisible by remember {
//        derivedStateOf {
//            lazyState.isLastItemVisible
//        }
//    }
    val context = LocalContext.current
//    val state = viewModel.state
    val cellConfiguration = if (LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE) {
        GridCells.Adaptive(minSize = 175.dp)
    } else GridCells.Fixed(2)
    LaunchedEffect(true) {
//        if (lastItemVisible) {
//            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
//        }
        scope.launch {
            postsList.collect {
                itemsList.addAll(it)
            }
        }
    }
    ParentView {
        LazyVerticalGrid(contentPadding = PaddingValues(top = 50.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            state = lazyState,
            columns = cellConfiguration) {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Card(Modifier
                    .fillMaxWidth()
                    .height(200.dp), backgroundColor = Color.DarkGray) {
                    Text(text = "Header")
                }
            }
//            items(itemsList.size) {
//                val item = itemsList[it]
//                LazyItem(posts = item)
//            }
//            when (val state = postsPager.loadState.prepend) {
//                is LoadState.NotLoading -> Unit
//                is LoadState.Loading -> {
//                    Loading()
//                }
//                is LoadState.Error -> {
//                    Error(message = state.error.message ?: "")
//                }
//            }

//            when (val state = postsPager.loadState.refresh) {
//                is LoadState.NotLoading -> Unit
//                is LoadState.Loading -> {
//                    Loading()
//                }
//                is LoadState.Error -> {
//                    Error(message = state.error.message ?: "")
//                }
//            }

            itemsC(items = postsPager) { item ->
                item?.let { it -> LazyItem(posts = it) }
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
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
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

//val LazyGridState.isLastItemVisible: Boolean
//    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

inline fun <T : Any> LazyGridScope.itemsC(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit,
) {
    items(count = items.itemCount) { index ->
        itemContent(items[index])
    }
}