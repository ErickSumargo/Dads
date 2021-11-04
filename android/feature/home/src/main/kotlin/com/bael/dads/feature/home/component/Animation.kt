package com.bael.dads.feature.home.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun Animation(
    modifier: Modifier = Modifier,
    source: LottieCompositionSpec,
    loop: Int = LottieConstants.IterateForever
) {
    val composition by rememberLottieComposition(spec = source)
    val animationState by animateLottieCompositionAsState(
        composition,
        iterations = loop,
    )

    LottieAnimation(
        composition = composition,
        progress = animationState,
        modifier = modifier
    )
}