package com.bael.dads.feature.home.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.ListenableWorker.Result.retry
import androidx.work.WorkerParameters
import com.bael.dads.feature.home.notification.NewFeedReminderNotification
import com.bael.dads.feature.home.preference.Preference
import com.bael.dads.lib.data.response.Response.Success
import com.bael.dads.lib.domain.interactor.home.LoadDadJokeFeedInteractor
import com.bael.dads.lib.domain.interactor.home.LoadDadJokeInteractor
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.notification.NotificationPublisher
import com.bael.dads.lib.worker.BaseWorker
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
    private val loadDadJokeInteractor: LoadDadJokeInteractor,
    private val loadDadJokeFeedInteractor: LoadDadJokeFeedInteractor,
    private val preference: Preference,
    private val notificationPublisher: NotificationPublisher
) : BaseWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val id = inputData.getInt(INPUT_CURSOR_ID, 0)
        var cursor: DadJoke? = null

        loadDadJokeInteractor(id)
            .filter { response ->
                response is Success
            }.collect { response ->
                cursor = (response as Success).data
            }

        loadDadJokeFeedInteractor(cursor, limit = 10)
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

    private fun pushNotification(context: Context, dadJokes: List<DadJoke>) {
        if (!preference.isNewFeedReminderEnabled) return

        val notification = NewFeedReminderNotification(context, dadJokes)
        notificationPublisher.publish(notification)
    }

    internal companion object {
        const val TAG: String = "FetchDadJokeFeedWorker"
        const val INPUT_CURSOR_ID: String = "cursor_id"
    }
}
