package com.bael.dads.library.instrumentation.screen

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bael.dads.library.instrumentation.activity.MainTestActivity
import com.bael.dads.library.presentation.local.LocalAnimationLoop
import com.bael.dads.library.threading.Thread
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalMaterialApi
abstract class ScreenTestSuit(private val destination: String) {
    @get:Rule(order = 1)
    internal val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    internal val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule(order = 3)
    internal val composeRule = createAndroidComposeRule<MainTestActivity>()

    @Inject
    internal lateinit var thread: Thread

    abstract fun NavGraphBuilder.navigation(
        route: String,
        sheetContent: (@Composable () -> Unit) -> Unit
    )

    abstract val route: String

    private var sheetContent: (@Composable () -> Unit)? by mutableStateOf(null)

    @Before
    internal fun setup() {
        hiltRule.inject()
    }

    protected fun test(
        useUnmergedTree: Boolean = false,
        onSetup: suspend () -> Unit = {},
        onRun: suspend (Context, ComposeTestRule) -> Unit,
        onDispose: suspend () -> Unit = {}
    ) {
        val coroutineDispatcher = thread.main as TestCoroutineDispatcher
        coroutineDispatcher.runBlockingTest {
            onSetup()

            composeRule.setContent {
                val bottomSheetState =
                    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

                val coroutineScope = rememberCoroutineScope()

                ModalBottomSheetLayout(
                    sheetContent = {
                        if (sheetContent == null) {
                            Text("Empty")
                        } else {
                            sheetContent!!()
                        }
                    },
                    sheetState = bottomSheetState,
                ) {
                    CompositionLocalProvider(LocalAnimationLoop provides false) {
                        NavHost(
                            navController = rememberNavController(),
                            startDestination = destination,
                            builder = {
                                navigation(this@ScreenTestSuit.route) {
                                    sheetContent = it

                                    coroutineScope.launch {
                                        bottomSheetState.show()
                                    }
                                }
                            }
                        )
                    }
                }
            }

            composeRule.onRoot(useUnmergedTree).printToLog("Semantics")

            onRun(composeRule.activity, composeRule)
            onDispose()
        }
    }

    @After
    internal fun tearDown() {
        thread.reset()
    }
}
