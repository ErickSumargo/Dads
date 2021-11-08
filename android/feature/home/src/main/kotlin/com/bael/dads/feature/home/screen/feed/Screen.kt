package com.bael.dads.feature.home.screen.feed

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.component.Animation
import com.bael.dads.feature.home.component.EmptyState
import com.bael.dads.feature.home.component.Loading
import com.bael.dads.feature.home.component.NoNetwork
import com.bael.dads.feature.home.component.ServerError
import com.bael.dads.feature.home.local.animation
import com.bael.dads.feature.home.local.icon
import com.bael.dads.feature.home.local.locale
import com.bael.dads.feature.home.screen.sharepreview.SharePreviewRoute
import com.bael.dads.library.presentation.color.Ruby
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.fold
import kotlin.math.absoluteValue

/**
 * Created by ErickSumargo on 01/11/21.
 */

private const val FEED_LIMIT = 10

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
internal fun FeedScreen(
    uiState: FeedState,
    sheetContent: (@Composable () -> Unit) -> Unit
) {
    println("${System.currentTimeMillis()}: Feed")
    SideEffect {
        println("${System.currentTimeMillis()}: Feed -> SideEffect")
    }

    HorizontalPager(
        count = uiState.feed.size,
        state = uiState.pagerState,
//        key = { uiState.feed[it].hashCode() },
        contentPadding = PaddingValues(
            horizontal = 48.dp,
            vertical = 24.dp
        ),
    ) { page ->
        when (val item = uiState.feed[page]) {
            is Feed.Loading -> {
                Loading()
            }
            is Feed.NoNetwork -> {
                NoNetwork(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onClick = {
                        uiState.loadFeed(
                            cursor = uiState.cursor?.dadJoke,
                            limit = FEED_LIMIT
                        )
                    }
                )
            }
            is Feed.ServerError -> {
                ServerError(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onClick = {
                        uiState.loadFeed(
                            cursor = uiState.cursor?.dadJoke,
                            limit = FEED_LIMIT
                        )
                    }
                )
            }
            is Feed.NewFeedReminder -> {
                uiState.scheduleFeedWorker(cursor = uiState.cursor?.dadJoke)
                NewFeedReminder(modifier = Modifier.padding(horizontal = 8.dp))
            }
            is Feed.Post -> {
                Post(
                    dadJoke = item.dadJoke,
                    isExpanded = uiState.isPostExpanded(dadJoke = item.dadJoke),
                    isLike = uiState.isLikePost(dadJoke = item.dadJoke),
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                            scaleX = 1f - pageOffset * 0.1f
                            scaleY = 1f - pageOffset * 0.1f
                        },
                    onClick = { expanded ->
                        uiState.expandPost(
                            dadJoke = item.dadJoke,
                            expanded = expanded
                        )
                    },
                    onLikeClick = { isLike ->
                        uiState.likePost(
                            dadJoke = item.dadJoke,
                            isLike = isLike
                        )
                    },
                    onSharePreviewClick = {
                        sheetContent { SharePreviewRoute(dadJoke = item.dadJoke) }
                    }
                )
            }
        }
    }

    // Initial Load
    LaunchedEffect(key1 = Unit) {
        uiState.loadFeed(
            cursor = null,
            limit = FEED_LIMIT
        )
    }

    OnPageSnapListener(
        pagerState = uiState.pagerState,
        callback = { prevPage, nextPage ->
            uiState.setPostSeen(prevPage)

            if (nextPage == uiState.feed.size - 1) {
                uiState.loadFeed(
                    cursor = uiState.cursor?.dadJoke,
                    limit = FEED_LIMIT
                )
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
private fun OnPageSnapListener(
    pagerState: PagerState,
    callback: (Int, Int) -> Unit
) {
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }
            .fold(initial = -1) { current, next ->
                callback(current, next)
                next
            }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
private fun Post(
    dadJoke: DadJoke,
    isExpanded: Boolean,
    isLike: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit,
    onLikeClick: (Boolean) -> Unit,
    onSharePreviewClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = 16.dp),
        elevation = 6.dp,
    ) {
        AnimatedContent(
            targetState = isExpanded,
            transitionSpec = { applyExpandingTransition(isExpanded = targetState) }
        ) { isExpanded ->
            if (!isExpanded) {
                Setup(
                    setup = dadJoke.setup,
                    onClick = onClick
                )
            } else {
                Punchline(
                    punchline = dadJoke.punchline,
                    isLike = isLike,
                    onClick = onClick,
                    onLikeClick = onLikeClick,
                    onSharePreviewClick = onSharePreviewClick
                )
            }
        }
    }
}

@ExperimentalAnimationApi
private fun applyExpandingTransition(isExpanded: Boolean): ContentTransform {
    return if (isExpanded) {
        slideInHorizontally(
            initialOffsetX = { width -> (-width * 0.2f).toInt() },
            animationSpec = tween(200)
        ) + fadeIn(
            initialAlpha = 0.5f,
            animationSpec = tween(100)
        ) with
            slideOutHorizontally(
                targetOffsetX = { width -> (width * 0.2f).toInt() },
                animationSpec = tween(200)
            ) + fadeOut(
            animationSpec = tween(100)
        )
    } else {
        slideInHorizontally(
            initialOffsetX = { width -> (width * 0.2f).toInt() },
            animationSpec = tween(200)
        ) + fadeIn(
            initialAlpha = 0.5f,
            animationSpec = tween(100)
        ) with
            slideOutHorizontally(
                targetOffsetX = { width -> (-width * 0.2f).toInt() },
                animationSpec = tween(200)
            ) + fadeOut(
            animationSpec = tween(100)
        )
    }
}

@Composable
private fun Setup(
    setup: String,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(state = ScrollState(initial = 0))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick(true) }
            ),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = setup,
            modifier = Modifier.padding(all = 24.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 32.sp,
        )
    }
}

@ExperimentalAnimationApi
@Composable
private fun Punchline(
    punchline: String,
    isLike: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit,
    onLikeClick: (Boolean) -> Unit,
    onSharePreviewClick: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = ScrollState(initial = 0))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick(false) }
            )
    ) {
        val (punchlineRef, actionFooterRef) = createRefs()

        Text(
            text = punchline,
            modifier = Modifier.constrainAs(punchlineRef) {
                width = Dimension.fillToConstraints

                linkTo(
                    start = parent.start,
                    top = parent.top,
                    end = parent.end,
                    bottom = actionFooterRef.top,
                    startMargin = 24.dp,
                    topMargin = 24.dp,
                    endMargin = 24.dp,
                    bottomMargin = 24.dp,
                    horizontalBias = 0f
                )
            },
            color = MaterialTheme.colors.onSurface,
            fontSize = 32.sp
        )

        ActionFooter(
            isLike = isLike,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(actionFooterRef) {
                    width = Dimension.fillToConstraints

                    start.linkTo(anchor = parent.start, margin = 24.dp)
                    bottom.linkTo(anchor = parent.bottom, margin = 24.dp)
                },
            onLikeClick = onLikeClick,
            onSharePreviewClick = onSharePreviewClick
        )
    }
}

@ExperimentalAnimationApi
@Composable
private fun ActionFooter(
    isLike: Boolean,
    modifier: Modifier = Modifier,
    onLikeClick: (Boolean) -> Unit,
    onSharePreviewClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        AnimatedContent(
            targetState = isLike,
            transitionSpec = { applyLikeTransition() }
        ) { isLike ->
            if (isLike) {
                IconButton(
                    modifier = Modifier.size(28.dp),
                    onClick = { onLikeClick(false) },
                ) {
                    Icon(
                        painter = icon.like,
                        contentDescription = "Icon ${locale.like}",
                        tint = Ruby,
                    )
                }
            } else {
                IconButton(
                    modifier = Modifier.size(28.dp),
                    onClick = { onLikeClick(true) },
                ) {
                    Icon(
                        painter = icon.likeOutline,
                        contentDescription = "Icon ${locale.dislike}",
                        tint = MaterialTheme.colors.onSurface,
                    )
                }
            }
        }

        IconButton(
            modifier = Modifier.size(28.dp),
            onClick = onSharePreviewClick,
        ) {
            Icon(
                painter = icon.share,
                contentDescription = "Icon ${locale.share}",
                tint = MaterialTheme.colors.onSurface,
            )
        }
    }
}

@ExperimentalAnimationApi
private fun applyLikeTransition(): ContentTransform {
    return scaleIn() with scaleOut()
}

@Composable
private fun NewFeedReminder(modifier: Modifier = Modifier) {
    EmptyState(
        modifier = modifier,
        image = {
            Animation(
                modifier = Modifier.size(120.dp),
                source = animation.newFeedReminder
            )
        },
        description = {
            Text(
                text = locale.newFeedReminderDescription,
                color = MaterialTheme.colors.onBackground,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    )
}
