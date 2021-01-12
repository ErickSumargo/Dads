package com.bael.dads.lib.asset.ext

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat.getDrawable

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun Context.drawable(@DrawableRes resource: Int): Drawable {
    val drawable = getDrawable(this, resource)
    return drawable ?: ColorDrawable()
}
