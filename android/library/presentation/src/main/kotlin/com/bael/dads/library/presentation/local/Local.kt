package com.bael.dads.library.presentation.local

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.bael.dads.library.presentation.color.DadsColor
import com.bael.dads.library.presentation.color.DadsColorImpl

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal val LocalDadsColor = staticCompositionLocalOf<DadsColor> {
    DadsColorImpl()
}

val LocalAnimationLoop = compositionLocalOf { true }

val color: DadsColor
    @Composable
    get() = LocalDadsColor.current
