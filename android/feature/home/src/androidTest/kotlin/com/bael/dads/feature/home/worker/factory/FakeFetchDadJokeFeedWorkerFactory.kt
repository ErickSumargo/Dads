package com.bael.dads.feature.home.worker.factory

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeUseCase
import com.bael.dads.feature.home.notification.factory.NewFeedReminderNotificationFactory
import com.bael.dads.feature.home.worker.FetchDadJokeFeedWorker
import com.bael.dads.library.preference.Preference
import com.bael.dads.library.presentation.notification.NotificationPublisher
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 15/05/21.
 */

@Singleton
internal class FakeFetchDadJokeFeedWorkerFactory @Inject constructor(
    private val loadDadJokeUseCase: LoadDadJokeUseCase,
    private val loadDadJokeFeedUseCase: LoadDadJokeFeedUseCase,
    private val preference: Preference,
    private val notificationPublisher: NotificationPublisher,
    private val newFeedReminderNotificationFactory: NewFeedReminderNotificationFactory
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return FetchDadJokeFeedWorker(
            appContext,
            workerParameters,
            loadDadJokeUseCase,
            loadDadJokeFeedUseCase,
            preference,
            notificationPublisher,
            newFeedReminderNotificationFactory
        )
    }
}
