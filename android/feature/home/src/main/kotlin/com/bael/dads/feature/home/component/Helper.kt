package com.bael.dads.feature.home.component

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun captureBitmap(
    context: Context,
    content: @Composable () -> Unit,
): () -> Bitmap {
    val composeView = remember { ComposeView(context) }

    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        }
    )
    return { composeView.drawToBitmap() }
}
