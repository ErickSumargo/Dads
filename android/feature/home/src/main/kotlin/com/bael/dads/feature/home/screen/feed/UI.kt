@file:Suppress("WrongConstant")

package com.bael.dads.feature.home.screen.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy.KEEP
import androidx.work.NetworkType.CONNECTED
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.adapter.DadJokeFeedAdapter
import com.bael.dads.feature.home.adapter.diffcallback.DadJokeDiffCallback
import com.bael.dads.feature.home.animation.reminder
import com.bael.dads.feature.home.databinding.ScreenFeedBinding
import com.bael.dads.feature.home.databinding.ScreenFeedBinding.inflate
import com.bael.dads.feature.home.worker.FetchDadJokeFeedWorker
import com.bael.dads.feature.home.worker.FetchDadJokeFeedWorker.Companion.INPUT_CURSOR_ID
import com.bael.dads.library.presentation.ext.readText
import com.bael.dads.library.presentation.fragment.BaseFragment
import com.bael.dads.library.presentation.widget.animation.error
import com.bael.dads.library.presentation.widget.animation.loading
import com.bael.dads.library.presentation.widget.animation.noInternet
import com.bael.dads.library.presentation.widget.listener.OnPageSnapListener
import com.bael.dads.library.presentation.widget.recyclerview.adapter.ResponseStateAdapter
import com.bael.dads.library.presentation.widget.recyclerview.adapter.data.ResponseState
import com.bael.dads.library.presentation.widget.recyclerview.adapter.data.ResponseState.AnimationProperty
import com.bael.dads.library.presentation.widget.viewpager.transformer.StackTransformer
import com.bael.dads.shared.exception.NoNetworkException
import com.bael.dads.shared.response.Response
import com.bael.dads.shared.response.Response.Empty
import com.bael.dads.shared.response.Response.Error
import com.bael.dads.shared.response.Response.Loading
import com.bael.dads.shared.response.Response.Success
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit.MINUTES
import javax.inject.Inject
import com.bael.dads.feature.home.worker.FetchDadJokeFeedWorker.Companion.TAG as FetchDadJokeFeedWorkerTag
import com.bael.dads.library.presentation.R as RPresentation

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseFragment<ScreenFeedBinding, Renderer, Event, ViewModel>(),
    Renderer {
    @Inject
    lateinit var workManager: WorkManager

    override val viewModel: ViewModel by viewModels()

    private val adapter: ConcatAdapter by lazy {
        ConcatAdapter()
    }

    private val loadingStateAdapter: ResponseStateAdapter by lazy {
        ResponseStateAdapter(
            state = ResponseState(
                animation = AnimationProperty(
                    resource = loading,
                    playLoop = true
                )
            )
        )
    }

    private val noNetworkStateAdapter: ResponseStateAdapter by lazy {
        ResponseStateAdapter(
            state = ResponseState(
                animation = AnimationProperty(
                    resource = noInternet,
                    playLoop = true
                ),
                description = readText(resId = RPresentation.string.no_internet_description),
                actionText = readText(resId = RPresentation.string.try_again)
            ),
            onClickActionListener = {
                val cursor = feedAdapter.lastItem()
                viewModel.loadDadJokeFeed(cursor, limit = LOAD_FEED_LIMIT)
            }
        )
    }

    private val serverErrorStateAdapter: ResponseStateAdapter by lazy {
        ResponseStateAdapter(
            state = ResponseState(
                animation = AnimationProperty(
                    resource = error,
                    playLoop = false
                ),
                description = readText(resId = RPresentation.string.server_error_description),
                actionText = "${readText(resId = RPresentation.string.try_again)}?"
            ),
            onClickActionListener = {
                val cursor = feedAdapter.lastItem()
                viewModel.loadDadJokeFeed(cursor, limit = LOAD_FEED_LIMIT)
            }
        )
    }

    private val reminderAdapter: ResponseStateAdapter by lazy {
        ResponseStateAdapter(
            state = ResponseState(
                animation = AnimationProperty(
                    resource = reminder,
                    playLoop = true
                ),
                description = readText(resId = R.string.new_feed_reminder_description)
            )
        )
    }

    private val feedAdapter: DadJokeFeedAdapter by lazy {
        DadJokeFeedAdapter(
            diffCallback = DadJokeDiffCallback(),
            lifecycleOwner = viewLifecycleOwner,
            onClickLikeListener = { dadJoke, favored ->
                viewModel.favorDadJoke(dadJoke, favored)
            },
            onClickShareListener = { dadJoke ->
                shareDadJoke(dadJoke)
            },
            onReachEndOfItemsListener = { dadJoke ->
                viewModel.loadDadJokeFeed(cursor = dadJoke, limit = LOAD_FEED_LIMIT)
            },
            onObserveItemListener = { dadJoke ->
                viewModel.observeDadJoke(dadJoke)
            }
        )
    }

    private val onPageSnapListener: OnPageSnapListener by lazy {
        OnPageSnapListener { previousPosition ->
            val dadJoke = feedAdapter.getItemAt(previousPosition)
            dadJoke ?: return@OnPageSnapListener

            if (dadJoke.seen) return@OnPageSnapListener
            viewModel.setDadJokeSeen(dadJoke)
        }
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ScreenFeedBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded(savedInstanceState: Bundle?) {
        setupView()
        if (savedInstanceState == null) {
            viewModel.loadDadJokeFeed(cursor = null, limit = LOAD_FEED_LIMIT)
        }
    }

    private fun setupView() {
        viewBinding.viewPager.also { pager ->
            pager.adapter = adapter
            pager.offscreenPageLimit = STACK_LIMIT

            pager.setPageTransformer(
                StackTransformer(stackLimit = STACK_LIMIT)
            )
            pager.registerOnPageChangeCallback(onPageSnapListener)
        }
    }

    override fun renderDadJokeFeed(responses: List<Response<List<DadJoke>>>) {
        responses.forEach { response ->
            when (response) {
                is Loading -> renderLoadingState()
                is Error -> renderErrorState(response)
                is Empty -> renderEmptyState()
                is Success -> renderSuccessState(response)
            }
        }
    }

    private fun renderLoadingState() {
        adapter.removeAdapter(noNetworkStateAdapter)
        adapter.removeAdapter(serverErrorStateAdapter)

        adapter.removeAdapter(reminderAdapter)

        adapter.addAdapter(loadingStateAdapter)
    }

    private fun renderErrorState(response: Error) {
        adapter.removeAdapter(loadingStateAdapter)

        adapter.addAdapter(
            when (response.error) {
                is NoNetworkException -> noNetworkStateAdapter
                else -> serverErrorStateAdapter
            }
        )
    }

    private fun renderEmptyState() {
        adapter.removeAdapter(loadingStateAdapter)

        adapter.addAdapter(reminderAdapter)

        startFetchDadJokeFeedWorker()
    }

    private fun renderSuccessState(response: Success<List<DadJoke>>) {
        adapter.removeAdapter(loadingStateAdapter)

        adapter.addAdapter(0, feedAdapter)
        feedAdapter.submitList(response.data)
    }

    private fun shareDadJoke(dadJoke: DadJoke) {
        val direction = UIDirections.showSharePreviewSheet(dadJoke)
        navigate(direction)
    }

    private fun startFetchDadJokeFeedWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(CONNECTED)
            .build()

        val inputData = workDataOf(
            INPUT_CURSOR_ID to feedAdapter.lastItem()?.id
        )

        val workRequest = PeriodicWorkRequestBuilder<FetchDadJokeFeedWorker>(
            repeatInterval = 30,
            repeatIntervalTimeUnit = MINUTES,
            flexTimeInterval = 15,
            flexTimeIntervalUnit = MINUTES
        )
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        workManager.enqueueUniquePeriodicWork(
            FetchDadJokeFeedWorkerTag,
            KEEP,
            workRequest
        )
    }

    override suspend fun action(event: Event) {}

    private companion object {
        const val STACK_LIMIT: Int = 3

        const val LOAD_FEED_LIMIT: Int = 10
    }
}
