package com.bael.dads.feature.home.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalPagerApi
@Composable
internal fun rememberHomeState(
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) = remember { HomeState(pagerState, coroutineScope) }

@ExperimentalPagerApi
internal class HomeState(
    val pagerState: PagerState,
    val coroutineScope: CoroutineScope
) {
    var shouldShowSearchBox: Boolean by mutableStateOf(false)
        private set

    var searchQuery: String by mutableStateOf("")
        private set

    init {
        observePageChanged()
    }

    private fun observePageChanged() {
        coroutineScope.launch {
            snapshotFlow { pagerState.currentPage }
                .collect { page ->
                    if (page == 0) {
                        clearSearchQuery()
                    }
                }
        }
    }

    fun swipeToPage(position: Int) {
        coroutineScope.launch {
            pagerState.scrollToPage(page = position)
        }
    }

    fun showSearchBox() {
        shouldShowSearchBox = true
    }

    fun updateSearchQuery(newSearchQuery: String) {
        searchQuery = newSearchQuery
    }

    fun clearSearchQuery() {
        searchQuery = ""
        shouldShowSearchBox = false
    }
}
