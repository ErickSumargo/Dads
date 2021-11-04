package com.bael.dads.feature.home.screen.sharepreview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal data class State(val dadJoke: DadJoke?) : BaseState()

@Composable
fun rememberSharePreviewState(dadJoke: DadJoke) =
    remember { SharePreviewState(dadJoke) }

class SharePreviewState(
    val dadJoke: DadJoke
)
