package com.bael.dads.feature.home.screen.sharepreview

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.component.Notch
import com.bael.dads.feature.home.component.captureBitmap
import com.bael.dads.feature.home.local.icon
import com.bael.dads.feature.home.local.locale
import com.bael.dads.library.presentation.color.Silver

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun SharePreviewScreen(uiState: SharePreviewState) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
            .verticalScroll(state = ScrollState(initial = 0))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Notch(
                size = DpSize(width = 24.dp, height = 4.dp),
                cornerRadius = 4.dp,
                color = Silver
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = locale.preview,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.onSurface,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        val snapshot = captureBitmap(context) {
            DadJoke(
                dadJoke = uiState.dadJoke,
                modifier = Modifier.padding(all = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Share(
            onClick = {
                uiState.share(
                    context,
                    fileName = "image_${uiState.dadJoke.id}.png",
                    bitmap = snapshot()
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun DadJoke(
    dadJoke: DadJoke,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
    ) {
        Content(
            dadJoke = dadJoke,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
        )
    }
}

@Composable
private fun Content(
    dadJoke: DadJoke,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (setupRef, punchlineRef, logoRef) = createRefs()

        Text(
            text = dadJoke.setup,
            modifier = Modifier.constrainAs(setupRef) {
                start.linkTo(anchor = parent.start)
                top.linkTo(anchor = parent.top)
            },
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = dadJoke.punchline,
            modifier = Modifier.constrainAs(punchlineRef) {
                start.linkTo(anchor = parent.start)
                top.linkTo(anchor = setupRef.bottom, margin = 16.dp)
            },
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Image(
            painter = icon.logo,
            contentDescription = locale.logo,
            modifier = Modifier
                .size(width = 36.dp, height = 24.dp)
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 4.dp)
                .constrainAs(logoRef) {
                    top.linkTo(anchor = punchlineRef.bottom, margin = 16.dp)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = parent.bottom)
                },
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
        )
    }
}

@Composable
private fun Share(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = locale.share,
            modifier = Modifier.clickable { onClick() },
            color = MaterialTheme.colors.primary,
            fontSize = 16.sp
        )
    }
}
