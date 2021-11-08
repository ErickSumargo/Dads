package com.bael.dads.library.presentation.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.bael.dads.library.presentation.R

/**
 * Created by ErickSumargo on 01/11/21.
 */

interface DadsColor {
    val darkNight: Color @Composable get

    val night: Color @Composable get

    val ruby: Color @Composable get

    val silver: Color @Composable get

    val teal: Color @Composable get

    val white: Color @Composable get
}

internal class DadsColorImpl : DadsColor {
    override val darkNight: Color
        @Composable get() = colorResource(id = R.color.darkNight)

    override val night: Color
        @Composable get() = colorResource(id = R.color.night)

    override val ruby: Color
        @Composable get() = colorResource(id = R.color.ruby)

    override val silver: Color
        @Composable get() = colorResource(id = R.color.silver)

    override val teal: Color
        @Composable get() = colorResource(id = R.color.teal)

    override val white: Color
        @Composable get() = colorResource(id = R.color.white)
}
