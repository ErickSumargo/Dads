package com.bael.dads.feature.home.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bael.dads.feature.home.component.Notch
import com.bael.dads.feature.home.local.locale
import com.bael.dads.library.presentation.local.color

/**
 * Created by ErickSumargo on 01/11/21.
 */

@Composable
internal fun SettingsScreen(uiState: SettingsState) {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Notch(
                size = DpSize(width = 24.dp, height = 4.dp),
                cornerRadius = 4.dp,
                color = color.silver
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        PreferenceGroup(name = locale.notification) {
            Preference(
                description = locale.newFeedReminder,
                isActive = uiState.isNewFeedReminderEnabled,
                onCheckedChange = uiState::updateNewFeedReminderActive
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        PreferenceGroup(name = locale.theme) {
            Preference(
                description = locale.nightMode,
                isActive = uiState.isNightThemeEnabled,
                onCheckedChange = uiState::updateNightThemeActive
            )
        }
    }
}

@Composable
private fun PreferenceGroup(
    modifier: Modifier = Modifier,
    name: String,
    items: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            color = MaterialTheme.colors.primary,
            fontSize = 14.sp
        )

        items()
    }
}

@Composable
private fun Preference(
    modifier: Modifier = Modifier,
    description: String,
    isActive: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (descRef, switchRef) = createRefs()

        Text(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(ref = descRef) {
                    width = Dimension.fillToConstraints

                    start.linkTo(anchor = parent.start)
                    top.linkTo(anchor = parent.top)
                    end.linkTo(anchor = switchRef.start, margin = 8.dp)
                    bottom.linkTo(anchor = parent.bottom)
                },
            fontSize = 16.sp
        )

        Switch(
            checked = isActive,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.constrainAs(ref = switchRef) {
                top.linkTo(anchor = parent.top)
                end.linkTo(anchor = parent.end)
                bottom.linkTo(anchor = parent.bottom)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary
            )
        )
    }
}
