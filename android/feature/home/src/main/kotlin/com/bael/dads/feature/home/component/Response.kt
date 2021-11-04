package com.bael.dads.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bael.dads.feature.home.local.animation
import com.bael.dads.feature.home.local.locale

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun Loading(modifier: Modifier = Modifier) {
    EmptyState(
        modifier = modifier,
        image = {
            Animation(source = animation.loading)
        }
    )
}

@Composable
internal fun NoNetwork(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    EmptyState(
        modifier = modifier,
        image = {
            Animation(
                modifier = Modifier.size(120.dp),
                source = animation.noNetwork
            )
        },
        description = {
            Text(
                text = locale.noNetworkDescription,
                color = MaterialTheme.colors.onBackground,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        action = {
            OutlinedButton(
                onClick = onClick,
                border = BorderStroke(
                    width = 1.2.dp,
                    color = MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = locale.tryAgain,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@Composable
internal fun ServerError(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    EmptyState(
        modifier = modifier,
        image = {
            Animation(
                modifier = Modifier.size(120.dp),
                source = animation.error,
                loop = 1
            )
        },
        description = {
            Text(
                text = locale.serverErrorDescription,
                color = MaterialTheme.colors.onBackground,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        action = {
            OutlinedButton(
                onClick = onClick,
                border = BorderStroke(
                    width = 1.2.dp,
                    color = MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = "${locale.tryAgain}?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}
