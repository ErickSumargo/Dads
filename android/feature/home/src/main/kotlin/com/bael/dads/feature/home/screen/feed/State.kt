package com.bael.dads.feature.home.screen.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.state.BaseState
import com.bael.dads.shared.response.Response
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal data class State(
    val responses: List<Response<List<DadJoke>>>
) : BaseState()

@ExperimentalPagerApi
@Composable
internal fun rememberFeedState(
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) = remember {
    FeedState(pagerState, coroutineScope)
}

@ExperimentalPagerApi
internal class FeedState(
    val pagerState: PagerState,
    private val coroutineScope: CoroutineScope
) {
    var content = mutableStateListOf(
        FeedContent.Post(
            dadJoke = DadJoke(
                id = 1,
                setup = "Setup 1",
                punchline = "Punchline",
                favored = false,
                seen = false,
                updatedAt = 0
            )
        ),
        FeedContent.NewFeedReminder,
        FeedContent.ServerError,
        FeedContent.NoNetwork,
        FeedContent.Loading
    )
        private set

    private val expandedPosts = mutableStateMapOf<DadJoke, Boolean>()

    private val likePosts = mutableStateMapOf<DadJoke, Boolean>()

    fun expandPost(dadJoke: DadJoke, expanded: Boolean) {
        expandedPosts[dadJoke] = expanded
    }

    fun isPostExpanded(dadJoke: DadJoke): Boolean {
        return expandedPosts[dadJoke] ?: false
    }

    fun likePost(dadJoke: DadJoke, isLike: Boolean) {
        likePosts[dadJoke] = isLike
    }

    fun isLikePost(dadJoke: DadJoke): Boolean {
        return likePosts.getOrPut(dadJoke, { dadJoke.favored })
    }
}

sealed class FeedContent {
    object Loading : FeedContent()

    object NoNetwork : FeedContent()

    object ServerError : FeedContent()

    object NewFeedReminder : FeedContent()

    data class Post(val dadJoke: DadJoke) : FeedContent()
}
