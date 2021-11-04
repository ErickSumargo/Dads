package com.bael.dads.feature.home.screen.seen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
internal fun SeenRoute(
    searchQuery: String,
    sheetContent: (@Composable () -> Unit) -> Unit
) {
    val uiState = rememberSeenState(
        swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false),
        scaffoldState = rememberScaffoldState(),
        lazyListState = rememberLazyListState(),
        coroutineScope = rememberCoroutineScope()
    )
    SeenScreen(searchQuery, uiState, sheetContent)
}
