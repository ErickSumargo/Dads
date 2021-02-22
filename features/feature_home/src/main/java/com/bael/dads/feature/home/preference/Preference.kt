package com.bael.dads.feature.home.preference

import com.bael.dads.lib.preference.Store
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class Preference @Inject constructor(store: Store) : Store by store {
    var isNewFeedReminderEnabled: Boolean
        get() = read(
            key = NEW_FEED_REMINDER_PREFERENCE,
            defaultValue = true
        )
        set(value) = write(
            key = NEW_FEED_REMINDER_PREFERENCE,
            value = value
        )

    var isNightTheme: Boolean
        get() = read(
            key = NIGHT_THEME_PREFERENCE,
            defaultValue = false
        )
        set(value) = write(
            key = NIGHT_THEME_PREFERENCE,
            value = value
        )

    private companion object {
        const val NEW_FEED_REMINDER_PREFERENCE: String = "new_feed_reminder"
        const val NIGHT_THEME_PREFERENCE: String = "night_theme"
    }
}
