package com.bael.dads.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bael.dads.DadsApplication
import com.bael.dads.feature.home.navigation.homeNavigation
import com.bael.dads.rememberDadsState
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberDadsState(
                bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
                navController = rememberNavController(),
                coroutineScope = rememberCoroutineScope()
            )

            DadsApplication(appState) {
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
