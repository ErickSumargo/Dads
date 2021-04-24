package com.bael.dads.feature.home.factory

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.notification.NewFeedReminderNotification
import dagger.assisted.AssistedFactory

@AssistedFactory
internal interface NewFeedReminderNotificationFactory {
    fun create(dadJokes: List<DadJoke>): NewFeedReminderNotification
}
