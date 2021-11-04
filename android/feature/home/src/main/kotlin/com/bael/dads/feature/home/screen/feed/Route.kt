package com.bael.dads.feature.home.screen.feed

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
internal fun FeedRoute(sheetContent: (@Composable () -> Unit) -> Unit) {
    val uiState = rememberFeedState(
        pagerState = rememberPagerState(),
        coroutineScope = rememberCoroutineScope()
    )
    FeedScreen(uiState, sheetContent)
}
