package com.bael.dads

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.bael.dads.library.preference.Preference
import com.bael.dads.theme.Theme
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalMaterialApi
@Composable
fun DadsApplication(appState: DadsState, content: @Composable () -> Unit) {
    Theme {
        ModalBottomSheetLayout(
            sheetContent = {
                if (appState.sheetContent == null) {
                    Text("Empty")
                } else {
                    appState.sheetContent!!()
                }
            },
            sheetState = appState.bottomSheetState,
            sheetShape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            ),
        ) {
            content()
        }
    }
}

abstract class DadsAndroidApplication : Application() {
    @Inject
    lateinit var preference: Preference

    override fun onCreate() {
        super.onCreate()
        setTheme()
    }

    private fun setTheme() {
        runBlocking {
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
