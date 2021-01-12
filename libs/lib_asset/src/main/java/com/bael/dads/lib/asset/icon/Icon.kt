package com.bael.dads.lib.asset.icon

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat.getColor
import com.bael.dads.lib.asset.R
import com.bael.dads.lib.asset.ext.drawable

/**
 * Created by ErickSumargo on 01/01/21.
 */

val Context.highlight: Drawable
    get() = drawable(R.drawable.ic_highlight)

val Context.history: Drawable
    get() = drawable(R.drawable.ic_history)

val Context.like: Drawable
    get() = drawable(R.drawable.ic_like)

val Context.likeOutline: Drawable
    get() = drawable(R.drawable.ic_like_outline)

val Context.search: Drawable
    get() = drawable(R.drawable.ic_search)

val Context.share: Drawable
    get() = drawable(R.drawable.ic_share)
