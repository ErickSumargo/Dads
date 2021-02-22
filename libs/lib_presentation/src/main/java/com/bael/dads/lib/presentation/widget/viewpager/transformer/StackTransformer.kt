package com.bael.dads.lib.presentation.widget.viewpager.transformer

import android.view.View
import androidx.core.view.ViewCompat.setElevation
import androidx.viewpager2.widget.ViewPager2.PageTransformer
import kotlin.math.abs

/**
 * Created by ErickSumargo on 01/01/21.
 */

class StackTransformer(private val stackLimit: Int) : PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val alphaFactor = -ALPHA_FACTOR * position + ALPHA
        val scaleFactor = -SCALE_FACTOR * position + SCALE

        when {
            position <= 0f -> {
                page.alpha = ALPHA + position
                page.scaleX = SCALE
                page.scaleY = SCALE
                page.translationX = TRANSLATION_X
            }
            position <= stackLimit - 1 -> {
                page.alpha = alphaFactor
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
                page.translationX = -(page.width / TRANSLATION_FACTOR) * position
            }
            else -> {
                page.alpha = ALPHA
                page.scaleX = SCALE
                page.scaleY = SCALE
                page.translationX = TRANSLATION_X
            }
        }
        setElevation(page, -abs(position))
    }

    private companion object {
        const val ALPHA = 1f
        const val ALPHA_FACTOR = 0f

        const val SCALE: Float = 1f
        const val SCALE_FACTOR: Float = .1f

        const val TRANSLATION_X = 0f
        const val TRANSLATION_FACTOR = 1.1f
    }
}
