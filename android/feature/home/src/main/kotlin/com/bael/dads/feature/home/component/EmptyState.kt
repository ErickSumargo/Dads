package com.bael.dads.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun EmptyState(
    modifier: Modifier = Modifier,
    image: @Composable () -> Unit = {},
    title: @Composable (() -> Unit)? = null,
    description: @Composable (() -> Unit)? = null,
    action: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        image()
        Spacer(modifier = Modifier.height(20.dp))

        if (title != null) {
            title()
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (description != null) {
            description()
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (action != null) {
            action()
        }
    }
}