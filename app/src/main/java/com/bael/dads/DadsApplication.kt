package com.bael.dads

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import com.bael.dads.lib.preference.Preference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class DadsApplication : Application() {
    @Inject
    internal lateinit var preference: Preference

    private val applicationScope: CoroutineScope
        get() = MainScope()

    override fun onCreate() {
        super.onCreate()
        setTheme()
    }

    private fun setTheme() {
        applicationScope.launch {
            val isNightTheme = preference.read(
                key = NIGHT_THEME_PREFERENCE,
                defaultValue = false
            )
            setDefaultNightMode(MODE_NIGHT_YES.takeIf { isNightTheme } ?: MODE_NIGHT_NO)
        }
    }

    private companion object {
        const val NIGHT_THEME_PREFERENCE: String = "night_theme"
    }
}
