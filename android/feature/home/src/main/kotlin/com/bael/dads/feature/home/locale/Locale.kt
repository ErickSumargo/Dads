package com.bael.dads.feature.home.locale

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bael.dads.feature.home.R

/**
 * Created by ErickSumargo on 01/11/21.
 */

internal interface HomeLocale {
    val clear: String @Composable get

    val dislike: String @Composable get

    val favoriteFilter: String @Composable get

    val favoriteFilterDescription: String @Composable get

    val feed: String @Composable get

    val like: String @Composable get

    val logo: String @Composable get

    val newFeedNotification: String @Composable get

    val newFeedNotificationDescription: String @Composable get

    val newFeedReminder: String @Composable get

    val newFeedReminderDescription: String @Composable get

    val nightMode: String @Composable get

    val noDataDescription: String @Composable get

    val noNetworkDescription: String @Composable get

    val notification: String @Composable get

    val preview: String @Composable get

    val search: String @Composable get

    val searchQueryHint: String @Composable get

    val seen: String @Composable get

    val serverErrorDescription: String @Composable get

    val settings: String @Composable get

    val share: String @Composable get

    val theme: String @Composable get

    val tryAgain: String @Composable get
}

internal class HomeLocaleImpl : HomeLocale {
    override val clear: String
        @Composable
        get() = stringResource(id = R.string.clear)

    override val dislike: String
        @Composable
        get() = stringResource(id = R.string.dislike)

    override val favoriteFilter: String
        @Composable
        get() = stringResource(id = R.string.favorite_filter)

    override val favoriteFilterDescription: String
        @Composable
        get() = stringResource(id = R.string.favorite_filter_description)

    override val feed: String
        @Composable
        get() = stringResource(id = R.string.feed)

    override val like: String
        @Composable
        get() = stringResource(id = R.string.like)

    override val logo: String
        @Composable
        get() = stringResource(id = R.string.logo)

    override val newFeedNotification: String
        @Composable
        get() = stringResource(id = R.string.new_feed_notification_title)

    override val newFeedNotificationDescription: String
        @Composable
        get() = stringResource(id = R.string.new_feed_notification_description)

    override val newFeedReminder: String
        @Composable
        get() = stringResource(id = R.string.new_feed_reminder)

    override val newFeedReminderDescription: String
        @Composable
        get() = stringResource(id = R.string.new_feed_reminder_description)

    override val nightMode: String
        @Composable
        get() = stringResource(id = R.string.night_mode)

    override val noDataDescription: String
        @Composable
        get() = stringResource(id = R.string.no_data_description)

    override val noNetworkDescription: String
        @Composable
        get() = stringResource(id = R.string.no_network_description)

    override val notification: String
        @Composable
        get() = stringResource(id = R.string.notification)

    override val preview: String
        @Composable
        get() = stringResource(id = R.string.preview)

    override val search: String
        @Composable
        get() = stringResource(id = R.string.search)

    override val searchQueryHint: String
        @Composable
        get() = stringResource(id = R.string.query_hint)

    override val seen: String
        @Composable
        get() = stringResource(id = R.string.seen)

    override val serverErrorDescription: String
        @Composable
        get() = stringResource(id = R.string.server_error_description)

    override val settings: String
        @Composable
        get() = stringResource(id = R.string.settings)

    override val share: String
        @Composable
        get() = stringResource(id = R.string.share)

    override val theme: String
        @Composable
        get() = stringResource(id = R.string.theme)

    override val tryAgain: String
        @Composable
        get() = stringResource(id = R.string.try_again)
}
