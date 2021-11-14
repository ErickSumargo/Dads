package com.bael.dads.feature.home.screen.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bael.dads.feature.home.local.icon
import com.bael.dads.feature.home.local.locale
import com.bael.dads.feature.home.screen.feed.FeedRoute
import com.bael.dads.feature.home.screen.seen.SeenRoute
import com.bael.dads.feature.home.screen.settings.SettingsRoute
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal data class TabAttr(
    val name: String,
    val icon: Painter
)

private val tabsAttr: List<TabAttr>
    @Composable
    get() = listOf(
        TabAttr(name = locale.feed, icon = icon.feed),
        TabAttr(name = locale.seen, icon = icon.seen)
    )

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
internal fun HomeScreen(
    uiState: HomeState,
    sheetContent: (@Composable () -> Unit) -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                modifier = Modifier.height(56.dp),
                pagerState = uiState.pagerState,
                searchQuery = uiState.searchQuery,
                shouldShowSearchBox = uiState.shouldShowSearchBox,
                onSearchClick = uiState::showSearchBox,
                onSearchQueryChange = uiState::updateSearchQuery,
                onSearchQueryClear = uiState::clearSearchQuery,
                onSettingsClick = { sheetContent { SettingsRoute() } }
            )
        },
        bottomBar = {
            BottomTabBar(
                pagerState = uiState.pagerState,
                tabsAttr = tabsAttr,
                onSelect = uiState::swipeToPage
            )
        },
    ) { paddingValues ->
        HorizontalPager(
            count = 2,
            modifier = Modifier.padding(paddingValues),
            state = uiState.pagerState
        ) { page ->
            when (page) {
                0 -> FeedRoute(sheetContent)
                1 -> SeenRoute(
                    searchQuery = uiState.searchQuery,
                    sheetContent = sheetContent
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    shouldShowSearchBox: Boolean,
    searchQuery: String,
    onSearchClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchQueryClear: () -> Unit,
    onSettingsClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (logoRef, searchBoxRef, searchRef, settingsRef) = createRefs()

        if (!shouldShowSearchBox) {
            Icon(
                painter = icon.logo,
                contentDescription = locale.logo,
                modifier = Modifier
                    .size(width = 45.dp, height = 30.dp)
                    .constrainAs(ref = logoRef) {
                        start.linkTo(anchor = parent.start, margin = 16.dp)
                        top.linkTo(anchor = parent.top)
                        bottom.linkTo(anchor = parent.bottom)
                    },
                tint = MaterialTheme.colors.onSurface
            )
        }

        if (shouldShowSearchBox) {
            val focusRequester = remember { FocusRequester() }

            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .constrainAs(ref = searchBoxRef) {
                        width = Dimension.fillToConstraints

                        start.linkTo(anchor = parent.start)
                        top.linkTo(anchor = parent.top)
                        end.linkTo(anchor = settingsRef.start, margin = 8.dp)
                        bottom.linkTo(anchor = parent.bottom)
                    }
                    .focusRequester(focusRequester),
                textStyle = TextStyle(fontSize = 18.sp),
                placeholder = {
                    Text(
                        text = locale.searchQueryHint,
                        fontSize = 18.sp
                    )
                },
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.size(16.dp),
                        onClick = onSearchQueryClear,
                    ) {
                        Icon(
                            painter = icon.clear,
                            contentDescription = "Icon ${locale.clear}",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent
                )
            )

            LaunchedEffect(key1 = Unit) {
                focusRequester.requestFocus()
            }
        } else if (pagerState.currentPage == 1 && !shouldShowSearchBox) {
            IconButton(
                onClick = onSearchClick,
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(ref = searchRef) {
                        top.linkTo(anchor = parent.top)
                        end.linkTo(anchor = settingsRef.start, margin = 16.dp)
                        bottom.linkTo(anchor = parent.bottom)
                    },
            ) {
                Icon(
                    painter = icon.search,
                    contentDescription = "Icon ${locale.search}",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }

        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier
                .size(20.dp)
                .constrainAs(ref = settingsRef) {
                    top.linkTo(anchor = parent.top)
                    end.linkTo(anchor = parent.end, margin = 16.dp)
                    bottom.linkTo(anchor = parent.bottom)
                },
        ) {
            Icon(
                painter = icon.settings,
                contentDescription = "Icon ${locale.settings}",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
private fun BottomTabBar(
    pagerState: PagerState,
    tabsAttr: List<TabAttr>,
    onSelect: (Int) -> Unit
) {
    Column {
        Divider()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.height(64.dp),
            backgroundColor = MaterialTheme.colors.background,
            indicator = {},
            divider = {}
        ) {
            tabsAttr.forEachIndexed { index, tabAttr ->
                BottomTab(
                    modifier = Modifier.semantics { contentDescription = "Tab ${tabAttr.name}" },
                    color = MaterialTheme.colors.primary,
                    selected = pagerState.currentPage == index,
                    tabAttr = tabAttr,
                    onClick = { onSelect(index) }
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun BottomTab(
    modifier: Modifier = Modifier,
    color: Color,
    selected: Boolean,
    tabAttr: TabAttr,
    onClick: () -> Unit
) {
    Tab(
        selected = true,
        onClick = onClick,
        modifier = modifier,
    ) {
        AnimatedContent(
            targetState = selected,
            transitionSpec = { applyTabTransition(selected = targetState) }
        ) { isSelected ->
            if (isSelected) {
                SelectedTab(tabAttr, color)
            } else {
                NormalTab(tabAttr, color)
            }
        }
    }
}

@ExperimentalAnimationApi
private fun applyTabTransition(selected: Boolean): ContentTransform {
    return if (selected) {
        slideInVertically(initialOffsetY = { height -> height }) with
            slideOutVertically(targetOffsetY = { height -> -height })
    } else {
        slideInVertically(initialOffsetY = { height -> -height }) with
            slideOutVertically(targetOffsetY = { height -> height })
    }
}

@Composable
private fun SelectedTab(
    tabAttr: TabAttr,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tabAttr.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Canvas(modifier = Modifier.padding(all = 12.dp)) {
            drawCircle(
                color = color,
                radius = 8f
            )
        }
    }
}

@Composable
private fun NormalTab(
    tabAttr: TabAttr,
    color: Color,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = tabAttr.icon,
        contentDescription = "Icon ${tabAttr.name}",
        modifier = modifier
            .alpha(0.8f)
            .size(28.dp),
        tint = color
    )
}
