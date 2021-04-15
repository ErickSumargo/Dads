package com.bael.dads.lib.presentation.notification

import android.app.Notification
import androidx.core.app.NotificationCompat.Builder

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface NotificationConfiguration {
    val id: Int

    val category: String

    val importance: Int

    fun createTemplate(templateBuilder: Builder): Notification
}
