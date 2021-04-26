package com.bael.dads.feature.home.factory

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.notification.NewFeedReminderNotification
import dagger.assisted.AssistedFactory

/**
 * Created by stef_ang on 24/04/21.
 */

@AssistedFactory
internal interface NewFeedReminderNotificationFactory {

    fun create(dadJokes: List<DadJoke>): NewFeedReminderNotification
}
