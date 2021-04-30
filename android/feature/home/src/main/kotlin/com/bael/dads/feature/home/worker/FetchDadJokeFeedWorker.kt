package com.bael.dads.feature.home.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.ListenableWorker.Result.retry
import androidx.work.WorkerParameters
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeUseCase
import com.bael.dads.feature.home.notification.NewFeedReminderNotification
import com.bael.dads.library.preference.Preference
import com.bael.dads.library.presentation.notification.NotificationPublisher
import com.bael.dads.library.worker.BaseWorker
import com.bael.dads.shared.response.Response.Success
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter

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
    private val notificationPublisher: NotificationPublisher
) : BaseWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val id = inputData.getInt(INPUT_CURSOR_ID, 0)
        var cursor: DadJoke? = null

        loadDadJokeUseCase(id)
            .filter { response ->
                response is Success
            }.collect { response ->
                cursor = (response as Success).data
            }

        loadDadJokeFeedUseCase(cursor, limit = 10)
            .filter { response ->
                response is Success
            }.collect { response ->
                pushNotification(
                    context = applicationContext,
                    dadJokes = (response as Success).data
                )
                cancelWork()
            }

        return retry()
    }

    private suspend fun pushNotification(context: Context, dadJokes: List<DadJoke>) {
        val isNewFeedReminderEnabled = preference.read(
            key = NEW_FEED_REMINDER_PREFERENCE,
            defaultValue = true
        )
        if (!isNewFeedReminderEnabled) return

        val notification = NewFeedReminderNotification(context, dadJokes)
        notificationPublisher.publish(notification)
    }

    internal companion object {
        const val TAG: String = "FetchDadJokeFeedWorker"

        const val INPUT_CURSOR_ID: String = "cursor_id"

        const val NEW_FEED_REMINDER_PREFERENCE: String = "new_feed_reminder"
    }
}
