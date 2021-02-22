@file:Suppress("DEPRECATION")

package com.bael.dads.lib.presentation.ext

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Html.fromHtml
import android.text.Spanned

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun String?.toRichText(): Spanned {
    val source = this.orEmpty()

    return if (SDK_INT >= N) fromHtml(source, FROM_HTML_MODE_LEGACY)
    else fromHtml(source)
}
