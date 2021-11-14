package com.bael.dads.feature.home.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.ListenableWorker.Result.retry
import androidx.work.ListenableWorker.Result.success
import androidx.work.WorkerParameters
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeUseCase
import com.bael.dads.feature.home.notification.factory.NewFeedReminderNotificationFactory
import com.bael.dads.library.preference.Preference
import com.bael.dads.library.presentation.notification.NotificationPublisher
import com.bael.dads.library.worker.BaseWorker
import com.bael.dads.shared.response.Response.Success
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltWorker
internal class FetchDadJokeFeedWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val loadDadJokeUseCase: LoadDadJokeUseCase,
    private val loadDadJokeFeedUseCase: LoadDadJokeFeedUseCase,
    private val preference: Preference,
    private val notificationPublisher: NotificationPublisher,
    private val newFeedReminderNotificationFactory: NewFeedReminderNotificationFactory
) : BaseWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val id = inputData.getInt(INPUT_CURSOR_ID, 0)
        val cursor = loadDadJokeUseCase(id)
            .filter { response ->
                response is Success
            }.map { response ->
                (response as Success).data
            }.firstOrNull()

        val dadJokes = loadDadJokeFeedUseCase(cursor, limit = 10)
            .filter { response ->
                response is Success
            }.map { response ->
                (response as Success).data
            }.firstOrNull().orEmpty()

        if (dadJokes.isNotEmpty()) {
            pushNotification(dadJokes)

            cancelWork()
            return success()
        }
        return retry()
    }

    private suspend fun pushNotification(dadJokes: List<DadJoke>) {
        val isNewFeedReminderEnabled = preference.read(
            key = NEW_FEED_REMINDER_PREFERENCE,
            defaultValue = true
        ).first()
        if (!isNewFeedReminderEnabled) return

        val notification = newFeedReminderNotificationFactory.create(dadJokes)
        notificationPublisher.publish(notification)
    }

    internal companion object {
        const val TAG: String = "FetchDadJokeFeedWorker"

        const val INPUT_CURSOR_ID: String = "cursor_id"

        const val NEW_FEED_REMINDER_PREFERENCE: String = "new_feed_reminder"
    }
}
