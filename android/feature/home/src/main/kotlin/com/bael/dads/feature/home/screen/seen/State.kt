package com.bael.dads.feature.home.screen.seen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.shared.response.Response
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun rememberSeenState(
    swipeRefreshState: SwipeRefreshState,
    scaffoldState: ScaffoldState,
    lazyListState: LazyListState,
    viewModel: SeenViewModel,
    coroutineScope: CoroutineScope
) = remember {
    SeenState(
        swipeRefreshState,
        scaffoldState,
        lazyListState,
        viewModel,
        coroutineScope
    )
}

internal class SeenState(
    val swipeRefreshState: SwipeRefreshState,
    val scaffoldState: ScaffoldState,
    val lazyListState: LazyListState,
    private val viewModel: SeenViewModel,
    private val coroutineScope: CoroutineScope
) {
    var seen: List<DadJoke> by mutableStateOf(listOf())
        private set

    val cursor: DadJoke?
        get() = seen.lastOrNull()

    var isFavoriteActive by mutableStateOf(false)
        private set

    val isRefreshing: Boolean
        get() = swipeRefreshState.isRefreshing

    var isLoadingMore: Boolean by mutableStateOf(false)
        private set

    private val expandedPosts = mutableStateMapOf<DadJoke, Boolean>()

    private val likePosts = mutableStateMapOf<DadJoke, Boolean>()

    init {
        observeSeen()
    }

    private fun observeSeen() {
        viewModel.seen
            .filter { it.isNotEmpty() }
            .onEach(::updateSeen)
            .flowOn(context = viewModel.thread.default)
            .launchIn(scope = coroutineScope)
    }

    fun expandPost(dadJoke: DadJoke, expanded: Boolean) {
        expandedPosts[dadJoke] = expanded
    }

    fun isPostExpanded(dadJoke: DadJoke): Boolean {
        return expandedPosts[dadJoke] ?: false
    }

    fun likePost(dadJoke: DadJoke, isLike: Boolean) {
        likePosts[dadJoke] = isLike
        viewModel.favorDadJoke(dadJoke, isLike)
    }

    fun isLikePost(dadJoke: DadJoke): Boolean {
        return likePosts.getOrPut(dadJoke, { dadJoke.favored })
    }

    fun activateFilter(isActive: Boolean) {
        isFavoriteActive = isActive
    }

    fun loadSeen(
        term: String,
        cursor: DadJoke?,
        limit: Int,
        favoredOnly: Boolean
    ) = viewModel.loadSeenDadJoke(term, cursor, limit, favoredOnly)

    private fun updateSeen(responses: List<Response<List<DadJoke>>>) {
        if (responses.any { it is Response.Loading }) {
            if (responses.size == 1) {
                swipeRefreshState.isRefreshing = true
            } else {
                isLoadingMore = true
            }
        } else {
            seen = responses
                .filterIsInstance<Response.Success<List<DadJoke>>>()
                .flatMap { it.data }

            isLoadingMore = false
            swipeRefreshState.isRefreshing = false
        }
    }

    fun showMessage(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }
}
