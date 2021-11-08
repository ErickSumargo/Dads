package com.bael.dads

import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bael.dads.feature.home.navigation.homeNavigation
import com.bael.dads.library.presentation.color.Night
import com.bael.dads.theme.DadsTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class DadsAndroidApplication : Application()

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun DadsApplication(isNightTheme: Boolean) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isNightTheme
        )
    }

    DadsTheme(darkTheme = isNightTheme) {
        ProvideWindowInsets {
            val appState = rememberDadsState(
                bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
                navController = rememberNavController(),
                coroutineScope = rememberCoroutineScope()
            )

            ModalBottomSheetLayout(
                sheetContent = {
                    if (appState.sheetContent == null) {
                        Text("Empty")
                    } else {
                        appState.sheetContent!!()
                    }
                },
                modifier = Modifier.systemBarsPadding(),
                sheetState = appState.bottomSheetState,
                sheetShape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                ),
                sheetElevation = 0.dp,
            ) {
                NavHost(
                    navController = appState.navController,
                    startDestination = "home"
                ) {
                    homeNavigation(sheetContent = { appState.showBottomSheet(it) })
                }
            }
        }
    }
}
