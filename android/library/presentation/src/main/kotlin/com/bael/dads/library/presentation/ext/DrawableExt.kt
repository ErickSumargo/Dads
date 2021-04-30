package com.bael.dads.library.presentation.ext

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat.getDrawable

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun Context.drawable(@DrawableRes resId: Int): Drawable {
    val drawable = getDrawable(this, resId)
    return drawable ?: ColorDrawable()
}
