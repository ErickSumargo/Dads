package com.bael.dads.feature.home.screen.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun SettingsRoute() {
    val uiState = rememberSettingsState(
        viewModel = hiltViewModel(),
        coroutineScope = rememberCoroutineScope()
    )
    SettingsScreen(uiState)
}
