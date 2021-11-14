package com.bael.dads.feature.home.screen.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.shared.exception.NoNetworkException
import com.bael.dads.shared.response.Response
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

/**
 * Created by ErickSumargo on 01/11/21.
 */

@ExperimentalPagerApi
@Composable
internal fun rememberFeedState(
    pagerState: PagerState,
    viewModel: FeedViewModel,
    coroutineScope: CoroutineScope
) = remember {
    FeedState(pagerState, viewModel, coroutineScope)
}

@ExperimentalPagerApi
internal class FeedState(
    val pagerState: PagerState,
    private val viewModel: FeedViewModel,
    private val coroutineScope: CoroutineScope
) {
    var feed: List<Feed> by mutableStateOf(listOf())
        private set

    val cursor: Feed.Post?
        get() = feed.lastOrNull { it is Feed.Post } as? Feed.Post

    private val expandedPosts = mutableStateMapOf<DadJoke, Boolean>()

    private val likePosts = mutableStateMapOf<DadJoke, Boolean>()

    init {
        observeFeed()
    }

    private fun observeFeed() {
        snapshotFlow { viewModel.feed }
            .filter { it.isNotEmpty() }
            .map(::mapFeed)
            .onEach { feed = it }
            .launchIn(scope = coroutineScope)
    }

    private suspend fun mapFeed(responses: List<Response<List<DadJoke>>>): List<Feed> {
        return withContext(context = viewModel.thread.default) {
            responses.flatMap { response ->
                when (response) {
                    is Response.Loading -> {
                        listOf(Feed.Loading)
                    }
                    is Response.Error -> {
                        when (response.error) {
                            is NoNetworkException -> listOf(Feed.NoNetwork)
                            else -> listOf(Feed.ServerError)
                        }
                    }
                    is Response.Empty -> {
                        listOf(Feed.NewFeedReminder)
                    }
                    is Response.Success -> {
                        response.data.map(Feed::Post)
                    }
                }
            }
        }
    }

    fun loadFeed(cursor: DadJoke?, limit: Int) {
        viewModel.loadDadJokeFeed(cursor, limit)
    }

    fun expandPost(dadJoke: DadJoke, expanded: Boolean) {
        expandedPosts[dadJoke] = expanded
    }

    fun isPostExpanded(dadJoke: DadJoke): Boolean {
        return expandedPosts[dadJoke] ?: false
    }

    fun setPostSeen(page: Int) {
        if (page !in feed.indices) return
        val prevPost = feed[page] as? Feed.Post ?: return

        viewModel.setDadJokeSeen(dadJoke = prevPost.dadJoke)
    }

    fun likePost(dadJoke: DadJoke, isLike: Boolean) {
        likePosts[dadJoke] = isLike
        viewModel.likeDadJoke(dadJoke, isLike)
    }

    fun isLikePost(dadJoke: DadJoke): Boolean {
        return likePosts.getOrPut(dadJoke, { dadJoke.favored })
    }

    fun scheduleFeedWorker(cursor: DadJoke?) {
        viewModel.scheduleFeedWorker(cursor)
    }
}

internal sealed class Feed {
    object Loading : Feed()

    object NoNetwork : Feed()

    object ServerError : Feed()

    object NewFeedReminder : Feed()

    data class Post(val dadJoke: DadJoke) : Feed()
}
