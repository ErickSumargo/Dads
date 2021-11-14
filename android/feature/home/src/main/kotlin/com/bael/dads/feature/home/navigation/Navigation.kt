package com.bael.dads.feature.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bael.dads.feature.home.screen.home.HomeRoute
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
fun NavGraphBuilder.homeNavigation(
    startDestination: String = "/",
    sheetContent: (@Composable () -> Unit) -> Unit
) {
    navigation(startDestination, route = "home") {
        composable(route = "/") {
            HomeRoute(sheetContent)
        }
    }
}
