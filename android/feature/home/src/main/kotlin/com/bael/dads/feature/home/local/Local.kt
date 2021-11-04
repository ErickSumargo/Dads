package com.bael.dads.feature.home.local

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.bael.dads.feature.home.animation.HomeAnimation
import com.bael.dads.feature.home.animation.HomeAnimationImpl
import com.bael.dads.feature.home.icon.HomeIcon
import com.bael.dads.feature.home.icon.HomeIconImpl
import com.bael.dads.feature.home.locale.HomeLocale
import com.bael.dads.feature.home.locale.HomeLocaleImpl

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal val LocalHomeAnimation = staticCompositionLocalOf<HomeAnimation> {
    HomeAnimationImpl()
}

internal val LocalHomeIcon = staticCompositionLocalOf<HomeIcon> {
    HomeIconImpl()
}

internal val LocalHomeLocale = staticCompositionLocalOf<HomeLocale> {
    HomeLocaleImpl()
}

internal val animation: HomeAnimation
    @Composable
    get() = LocalHomeAnimation.current

internal val icon: HomeIcon
    @Composable
    get() = LocalHomeIcon.current

internal val locale: HomeLocale
    @Composable
    get() = LocalHomeLocale.current
