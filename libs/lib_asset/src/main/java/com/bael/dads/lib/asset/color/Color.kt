package com.bael.dads.lib.asset.color

import android.content.Context
import androidx.core.content.ContextCompat.getColor
import com.bael.dads.lib.asset.R

/**
 * Created by ErickSumargo on 01/01/21.
 */

val Context.dark: Int
    get() = getColor(this, R.color.dark)

val Context.red: Int
    get() = getColor(this, R.color.red)

val Context.teal: Int
    get() = getColor(this, R.color.teal)

val Context.white: Int
    get() = getColor(this, R.color.white)
