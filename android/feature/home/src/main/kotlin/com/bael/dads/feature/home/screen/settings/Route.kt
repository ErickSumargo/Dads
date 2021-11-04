package com.bael.dads.feature.home.screen.settings

import androidx.compose.runtime.Composable

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun SettingsRoute() {
    val uiState = rememberSettingsState()
    SettingsScreen(uiState)
}
