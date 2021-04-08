package com.bael.dads.lib.presentation.ext

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun Context.readDrawable(@DrawableRes resId: Int): Drawable {
    return drawable(resId)
}

fun Context.readText(@StringRes resId: Int): String {
    return getString(resId)
}
