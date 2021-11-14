package com.bael.dads.feature.home.animation

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.bael.dads.feature.home.R

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal interface HomeAnimation {
    val empty: LottieCompositionSpec

    val error: LottieCompositionSpec

    val loading: LottieCompositionSpec

    val newFeedReminder: LottieCompositionSpec

    val noNetwork: LottieCompositionSpec
}

internal class HomeAnimationImpl : HomeAnimation {
    override val empty: LottieCompositionSpec
        get() = LottieCompositionSpec.RawRes(resId = R.raw.anim_empty)

    override val error: LottieCompositionSpec
        get() = LottieCompositionSpec.RawRes(resId = R.raw.anim_error)

    override val loading: LottieCompositionSpec
        get() = LottieCompositionSpec.RawRes(resId = R.raw.anim_loading)

    override val newFeedReminder: LottieCompositionSpec
        get() = LottieCompositionSpec.RawRes(resId = R.raw.anim_reminder)

    override val noNetwork: LottieCompositionSpec
        get() = LottieCompositionSpec.RawRes(resId = R.raw.anim_no_network)
}
