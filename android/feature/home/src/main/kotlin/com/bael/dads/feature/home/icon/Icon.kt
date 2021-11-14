package com.bael.dads.feature.home.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.bael.dads.feature.home.R

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal interface HomeIcon {
    val clear: Painter @Composable get

    val feed: Painter @Composable get

    val like: Painter @Composable get

    val likeOutline: Painter @Composable get

    val logo: Painter @Composable get

    val search: Painter @Composable get

    val seen: Painter @Composable get

    val settings: Painter @Composable get

    val share: Painter @Composable get
}

internal class HomeIconImpl : HomeIcon {
    override val clear: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_clear)

    override val feed: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_feed)

    override val like: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_like)

    override val likeOutline: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_like_outline)

    override val logo: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_logo)

    override val search: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_search)

    override val seen: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_seen)

    override val settings: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_settings)

    override val share: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_share)
}
