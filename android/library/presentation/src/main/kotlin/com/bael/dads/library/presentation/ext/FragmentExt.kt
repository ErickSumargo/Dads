package com.bael.dads.library.presentation.ext

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Created by ErickSumargo on 01/01/21.
 */

val Fragment.screenHeight: Int
    get() = activity?.resources?.displayMetrics?.heightPixels ?: 0

fun Fragment.readDrawable(@DrawableRes resId: Int): Drawable {
    return requireContext().readDrawable(resId)
}

fun Fragment.readText(@StringRes resId: Int): String {
    return requireContext().readText(resId)
}
