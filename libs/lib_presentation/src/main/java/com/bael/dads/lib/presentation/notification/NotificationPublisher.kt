package com.bael.dads.lib.presentation.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import androidx.core.app.NotificationCompat.Builder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Singleton
class NotificationPublisher @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    fun publish(configuration: NotificationConfiguration) {
        val channel = createChannel(configuration)
        channel?.let(notificationManager::createNotificationChannel)

        val template = createTemplate(configuration)
        notificationManager.notify(configuration.id, template)
    }

    private fun createChannel(configuration: NotificationConfiguration): NotificationChannel? {
        if (SDK_INT < O) return null
        return NotificationChannel(
            context.packageName,
            configuration.category,
            configuration.importance
        )
    }

    private fun createTemplate(configuration: NotificationConfiguration): Notification {
        return configuration.createTemplate(
            templateBuilder = Builder(context, context.packageName)
        )
    }
}
