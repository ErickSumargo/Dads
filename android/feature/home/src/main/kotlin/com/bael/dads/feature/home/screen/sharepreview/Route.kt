package com.bael.dads.feature.home.screen.sharepreview

import androidx.compose.runtime.Composable
import com.bael.dads.domain.home.model.DadJoke

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun SharePreviewRoute(dadJoke: DadJoke) {
    val uiState = rememberSharePreviewState(dadJoke)
    SharePreviewScreen(uiState)
}