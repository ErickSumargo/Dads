package com.bael.dads.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import com.bael.dads.library.presentation.color.DarkNight
import com.bael.dads.library.presentation.color.Night
import com.bael.dads.library.presentation.color.Ruby
import com.bael.dads.library.presentation.color.Teal
import com.bael.dads.library.presentation.font.Typography

/**
 * Created by ErickSumargo on 01/11/21.
 */

private val LightPalette = lightColors(
    primary = Teal,
    primaryVariant = Teal,
    secondary = Teal,
    secondaryVariant = Teal,
    background = White,
    surface = White,
    onBackground = Night,
    onSurface = Night
)

private val DarkPalette = darkColors(
    primary = Teal,
    primaryVariant = Teal,
    secondary = Teal,
    secondaryVariant = Teal,
    background = DarkNight,
    surface = Night,
    onBackground = White,
    onSurface = White
)

@Composable
internal fun DadsTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkPalette.takeIf { darkTheme } ?: LightPalette,
        typography = Typography,
        content = content
    )
}
