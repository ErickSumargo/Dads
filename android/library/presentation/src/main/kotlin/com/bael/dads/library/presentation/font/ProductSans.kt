package com.bael.dads.library.presentation.font

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.bael.dads.library.presentation.R

/**
 * Created by ErickSumargo on 01/11/21.
 */

private val ProductSans = FontFamily(
    Font(R.font.product_sans, weight = FontWeight.Normal)
)

val Typography = Typography(defaultFontFamily = ProductSans)
