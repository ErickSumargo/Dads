package com.bael.dads

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import com.bael.dads.preference.Preference
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class DadsApplication : Application() {
    @Inject
    internal lateinit var preference: Preference

    override fun onCreate() {
        super.onCreate()
        setTheme()
    }

    private fun setTheme() {
        setDefaultNightMode(MODE_NIGHT_YES.takeIf { preference.isNightTheme } ?: MODE_NIGHT_NO)
    }
}
