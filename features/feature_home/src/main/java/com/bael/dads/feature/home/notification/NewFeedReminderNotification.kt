@file:Suppress("UnspecifiedImmutableFlag")

package com.bael.dads.feature.home.notification

import android.app.Notification
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.core.app.NotificationCompat.Builder
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_LOW
import com.bael.dads.feature.home.R
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.notification.NotificationConfiguration
import java.lang.Class.forName

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class NewFeedReminderNotification(
    private val context: Context,
    private val dadJokes: List<DadJoke>
) : NotificationConfiguration {
    override val id: Int
        get() = NEW_FEED_REMINDER_NOTIFICATION_ID

    override val category: String
        get() = context.getString(R.string.new_feed_reminder)

    override val importance: Int
        get() = IMPORTANCE_LOW

    override fun createTemplate(templateBuilder: Builder): Notification {
        val intent = Intent(
            context,
            forName("com.bael.dads.activity.MainActivity")
        ).also { intent ->
            intent.flags = FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK
        }

        val pendingIntent = getActivity(
            context,
            0,
            intent,
            FLAG_CANCEL_CURRENT
        )

        return templateBuilder
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(context.getString(R.string.new_feed_notification_title))
            .setContentText(
                context.getString(
                    R.string.new_feed_notification_description,
                    "${"+9".takeIf { dadJokes.size > 9 } ?: dadJokes.size}"
                )
            )
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
    }

    private companion object {
        const val NEW_FEED_REMINDER_NOTIFICATION_ID: Int = 1111
    }
}
