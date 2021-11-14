package com.bael.dads.feature.home.screen.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
internal fun HomeRoute(sheetContent: (@Composable () -> Unit) -> Unit) {
    val uiState = rememberHomeState(
        pagerState = rememberPagerState(),
        coroutineScope = rememberCoroutineScope()
    )
    HomeScreen(uiState, sheetContent)
}