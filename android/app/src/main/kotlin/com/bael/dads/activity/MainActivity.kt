package com.bael.dads.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bael.dads.DadsApplication
import com.bael.dads.library.preference.Preference
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var preference: Preference

    private var isNightThemeEnabled by mutableStateOf(false)

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeNightThemePreference()
        applyWindowInsets()

        setContent {
            DadsApplication(isNightTheme = isNightThemeEnabled)
        }
    }

    private fun observeNightThemePreference() {
        preference.read(
            key = NIGHT_THEME_PREFERENCE,
            defaultValue = false
        )
            .flowWithLifecycle(lifecycle, minActiveState = Lifecycle.State.RESUMED)
            .onEach { isEnabled -> isNightThemeEnabled = isEnabled }
            .launchIn(scope = lifecycleScope)
    }

    private fun applyWindowInsets() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private companion object {
        const val NIGHT_THEME_PREFERENCE = "night_theme"
    }
}
