package com.bael.dads.preference

import com.bael.dads.lib.preference.Store
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class Preference @Inject constructor(store: Store) : Store by store {
    val isNightTheme: Boolean
        get() = read(
            key = NIGHT_THEME_PREFERENCE,
            defaultValue = false
        )

    private companion object {
        const val NIGHT_THEME_PREFERENCE: String = "night_theme"
    }
}
