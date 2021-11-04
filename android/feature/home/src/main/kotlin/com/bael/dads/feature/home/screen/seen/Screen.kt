package com.bael.dads.feature.home.screen.seen

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
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.component.Animation
import com.bael.dads.feature.home.component.EmptyState
import com.bael.dads.feature.home.local.animation
import com.bael.dads.feature.home.local.icon
import com.bael.dads.feature.home.local.locale
import com.bael.dads.feature.home.screen.sharepreview.SharePreviewRoute
import com.bael.dads.library.presentation.color.Ruby
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
internal fun SeenScreen(
    searchQuery: String,
    uiState: SeenState,
    sheetContent: (@Composable () -> Unit) -> Unit
) {
    Scaffold(
        scaffoldState = uiState.scaffoldState,
        bottomBar = {
            if (uiState.isLoadingMore) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.primary
                )
            }
        },
        floatingActionButton = {
            val message = locale.favoriteFilterDescription

            FavoriteFilter(
                isActive = uiState.isFavoriteActive,
                onClick = { isActive ->
                    uiState.activateFilter(isActive)

                    if (!isActive) return@FavoriteFilter
                    uiState.showMessage(message)
                }
            )
        }
    ) {
        SwipeRefresh(
            state = uiState.swipeRefreshState,
            onRefresh = uiState::loadMore,
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    contentColor = MaterialTheme.colors.primary
                )
            }
        ) {
            when {
                uiState.content.isEmpty() -> {
                    Empty(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp)
                            .verticalScroll(state = rememberScrollState())
                    )
                }
                else -> {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(count = 2),
                        modifier = Modifier.fillMaxSize(),
                        state = uiState.lazyListState,
                        contentPadding = PaddingValues(all = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                    ) {
                        items(items = uiState.content) { dadJoke ->
                            Seen(
                                dadJoke = dadJoke,
                                isExpanded = uiState.isPostExpanded(dadJoke),
                                isLike = uiState.isLikePost(dadJoke),
                                modifier = Modifier.fillMaxSize(),
                                onClick = { expanded ->
                                    uiState.expandPost(dadJoke, expanded)
                                },
                                onLikeClick = { isLike ->
                                    uiState.likePost(dadJoke, isLike)
                                },
                                onSharePreviewClick = {
                                    sheetContent { SharePreviewRoute(dadJoke) }
                                }
                            )
                        }
                    }

                    onLoadMoreListener(
                        lazyListState = uiState.lazyListState,
                        callback = uiState::loadMore
                    )
                }
            }
        }
    }
}

private fun onLoadMoreListener(
    lazyListState: LazyListState,
    callback: () -> Unit
) {
    val isEndOfItems by derivedStateOf {
        val layoutInfo = lazyListState.layoutInfo
        layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
    }

    println("${System.currentTimeMillis()}: $isEndOfItems")
    if (!isEndOfItems) return
    callback()
}

@Composable
private fun FavoriteFilter(
    isActive: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit
) {
    FloatingActionButton(
        onClick = { onClick(!isActive) },
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Icon(
            painter = icon.like.takeIf { isActive } ?: icon.likeOutline,
            contentDescription = locale.favoriteFilter,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colors.primary
        )
    }
}

@ExperimentalAnimationApi
@Composable
private fun Seen(
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
        shape = RoundedCornerShape(size = 8.dp),
        elevation = 4.dp,
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
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick(true) }
            ),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = setup,
            modifier = Modifier.padding(all = 16.dp),
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
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
                    startMargin = 16.dp,
                    topMargin = 16.dp,
                    endMargin = 16.dp,
                    bottomMargin = 20.dp,
                    horizontalBias = 0f
                )
            },
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp
        )

        ActionFooter(
            isLike = isLike,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(actionFooterRef) {
                    width = Dimension.fillToConstraints

                    start.linkTo(anchor = parent.start, margin = 16.dp)
                    bottom.linkTo(anchor = parent.bottom, margin = 16.dp)
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
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        AnimatedContent(
            targetState = isLike,
            transitionSpec = { applyLikeTransition() }
        ) { isLike ->
            if (isLike) {
                IconButton(
                    modifier = Modifier.size(24.dp),
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
                    modifier = Modifier.size(24.dp),
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
            modifier = Modifier.size(24.dp),
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
private fun Empty(modifier: Modifier = Modifier) {
    EmptyState(
        modifier = modifier,
        image = {
            Animation(
                modifier = Modifier.size(180.dp),
                source = animation.empty
            )
        },
        description = {
            Text(
                text = locale.noDataDescription,
                color = MaterialTheme.colors.onBackground,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    )
}
