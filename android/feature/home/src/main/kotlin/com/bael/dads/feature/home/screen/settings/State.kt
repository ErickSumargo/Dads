package com.bael.dads.feature.home.screen.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bael.dads.library.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal class State : BaseState()

@Composable
internal fun rememberSettingsState() = remember { SettingsState() }

internal class SettingsState {
    var isNewFeedReminderActive: Boolean by mutableStateOf(true)
        private set

    var isNightModeActive: Boolean by mutableStateOf(false)
        private set

    fun updateNewFeedReminderActive(isActive: Boolean) {
        isNewFeedReminderActive = isActive
    }

    fun updateNightThemeActive(isActive: Boolean) {
        isNightModeActive = isActive
    }
}
