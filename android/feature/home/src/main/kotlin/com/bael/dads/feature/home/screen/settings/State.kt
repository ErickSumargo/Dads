package com.bael.dads.feature.home.screen.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun rememberSettingsState(
    viewModel: SettingsViewModel,
    coroutineScope: CoroutineScope,
) = remember { SettingsState(viewModel, coroutineScope) }

internal class SettingsState(
    private val viewModel: SettingsViewModel,
    private val coroutineScope: CoroutineScope
) {
    var isNewFeedReminderEnabled: Boolean by mutableStateOf(false)
        private set

    var isNightThemeEnabled: Boolean by mutableStateOf(false)
        private set

    init {
        observeNewFeedReminderPreference()
        observeNightThemePreference()
    }

    private fun observeNewFeedReminderPreference() {
        viewModel.isNewFeedReminderEnabled
            .onEach { isEnabled ->
                isNewFeedReminderEnabled = isEnabled
            }
            .launchIn(scope = coroutineScope)
    }

    private fun observeNightThemePreference() {
        viewModel.isNightThemeEnabled
            .onEach { isEnabled ->
                isNightThemeEnabled = isEnabled
            }
            .launchIn(scope = coroutineScope)
    }

    fun updateNewFeedReminderActive(isEnabled: Boolean) {
        coroutineScope.launch {
            viewModel.updateNewFeedReminderPreference(isEnabled)
        }
    }

    fun updateNightThemeActive(isEnabled: Boolean) {
        coroutineScope.launch {
            viewModel.updateNightThemePreference(isEnabled)
        }
    }
}
