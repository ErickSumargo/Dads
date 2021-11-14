package com.bael.dads.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.bael.dads.library.presentation.font.DadsTypography
import com.bael.dads.library.presentation.local.color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by ErickSumargo on 01/11/21.
 */

private val LightPalette: Colors
    @Composable get() = lightColors(
        primary = color.teal,
        primaryVariant = color.teal,
        secondary = color.teal,
        secondaryVariant = color.teal,
        background = color.white,
        surface = color.white,
        onBackground = color.night,
        onSurface = color.night
    )

private val DarkPalette: Colors
    @Composable get() = darkColors(
        primary = color.teal,
        primaryVariant = color.teal,
        secondary = color.teal,
        secondaryVariant = color.teal,
        background = color.darkNight,
        surface = color.night,
        onBackground = color.white,
        onSurface = color.white
    )

@Composable
internal fun DadsTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = color.darkNight.takeIf { darkTheme } ?: color.white,
        darkIcons = !darkTheme
    )

    MaterialTheme(
        colors = DarkPalette.takeIf { darkTheme } ?: LightPalette,
        typography = DadsTypography,
        content = content
    )
}
