package com.bael.dads.feature.home.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.bael.dads.feature.home.navigation.homeNavigation
import com.bael.dads.library.instrumentation.screen.ScreenTestSuit
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
internal abstract class HomeScreenTest : ScreenTestSuit(destination = "home") {
    override fun NavGraphBuilder.navigation(
        route: String,
        sheetContent: (@Composable () -> Unit) -> Unit
    ) = homeNavigation(route, sheetContent)
}
