package com.bael.dads.feature.home.screen.seen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.state.BaseState
import com.bael.dads.shared.response.Response
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal data class State(
    val responses: List<Response<List<DadJoke>>>,
    val isFavoriteFilterActivated: Boolean,
) : BaseState()

@Composable
internal fun rememberSeenState(
    swipeRefreshState: SwipeRefreshState,
    scaffoldState: ScaffoldState,
    lazyListState: LazyListState,
    coroutineScope: CoroutineScope
) = remember {
    SeenState(
        swipeRefreshState,
        scaffoldState,
        lazyListState,
        coroutineScope
    )
}

internal class SeenState(
    val swipeRefreshState: SwipeRefreshState,
    val scaffoldState: ScaffoldState,
    val lazyListState: LazyListState,
    private val coroutineScope: CoroutineScope
) {
    var content: List<DadJoke> = mutableStateListOf()
        private set

    var isFavoriteActive by mutableStateOf(false)
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    val isLoadingMore: Boolean
        get() = isLoading && content.isNotEmpty()

    init {
        content = (1 until 3).fold(
            initial = listOf()
        ) { source, i ->
            source + DadJoke(
                id = i,
                setup = "Setup $i",
                punchline = "Punchline $i",
                favored = false,
                seen = true,
                updatedAt = 0
            )
        }
    }

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

    fun activateFilter(isActive: Boolean) {
        isFavoriteActive = isActive
    }

    fun loadMore() {
        isLoading = true

        coroutineScope.launch(context = Dispatchers.IO) {
            delay(1000)
            isLoading = false
        }
    }

    fun showMessage(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }
}
