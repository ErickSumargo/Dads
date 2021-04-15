package com.bael.dads.lib.presentation.widget.listener

import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

/**
 * Created by ErickSumargo on 01/01/21.
 */

class OnPageSnapListener(
    private val callback: (Int) -> Unit
) : OnPageChangeCallback() {
    private var previousPosition: Int = -1

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        if (position != previousPosition) {
            callback(previousPosition)
        }
        previousPosition = position
    }
}
