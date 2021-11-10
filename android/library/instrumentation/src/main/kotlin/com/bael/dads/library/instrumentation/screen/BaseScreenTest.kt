package com.bael.dads.library.instrumentation.screen

import android.content.Context
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bael.dads.library.instrumentation.activity.MainTestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

/**
 * Created by ErickSumargo on 01/11/21.
 */

abstract class BaseScreenTest(private val destination: String) {
    @get:Rule(order = 1)
    internal val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeRule = createAndroidComposeRule<MainTestActivity>()

    abstract val NavGraphBuilder.navigation: Unit

    abstract val route: String

    @Before
    internal fun setup() {
        hiltRule.inject()
    }

    protected fun test(
        onSetup: () -> Unit = {},
        onRun: (Context, ComposeTestRule) -> Unit
    ) {
        onSetup()

        composeRule.setContent {
            NavHost(
                navController = rememberNavController(),
                startDestination = destination,
                route = route,
                builder = { navigation }
            )
        }

        onRun(composeRule.activity, composeRule)
    }
}
