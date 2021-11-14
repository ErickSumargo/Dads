package com.bael.dads.feature.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun Notch(
    modifier: Modifier = Modifier,
    size: DpSize,
    cornerRadius: Dp,
    color: Color
) {
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = color,
            topLeft = Offset(x = -(size.width / 2).toPx(), y = 0f),
            size = Size(width = size.width.toPx(), height = size.height.toPx()),
            cornerRadius = CornerRadius(cornerRadius.toPx())
        )
    }
}