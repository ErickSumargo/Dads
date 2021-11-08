package com.bael.dads.feature.home.screen.settings

import androidx.lifecycle.ViewModel
import com.bael.dads.library.preference.Preference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/11/21.
 */

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val preference: Preference
) : ViewModel() {
    val isNewFeedReminderEnabled: Flow<Boolean>
        get() = preference.read(key = NEW_FEED_REMINDER_PREFERENCE, defaultValue = true)

    val isNightThemeEnabled: Flow<Boolean>
        get() = preference.read(key = NIGHT_THEME_PREFERENCE, defaultValue = false)

    suspend fun updateNewFeedReminderPreference(isEnabled: Boolean) {
        preference.write(key = NEW_FEED_REMINDER_PREFERENCE, value = isEnabled)
    }

    suspend fun updateNightThemePreference(isEnabled: Boolean) {
        preference.write(key = NIGHT_THEME_PREFERENCE, value = isEnabled)
    }

    private companion object {
        const val NEW_FEED_REMINDER_PREFERENCE = "new_feed_reminder"

        const val NIGHT_THEME_PREFERENCE = "night_theme"
    }
}
