package com.bael.dads.feature.home.screen.seen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.bael.dads.feature.home.R
import com.bael.dads.feature.home.adapter.SeenDadJokeAdapter
import com.bael.dads.feature.home.adapter.diffcallback.DadJokeDiffCallback
import com.bael.dads.feature.home.databinding.ScreenSeenBinding
import com.bael.dads.feature.home.databinding.ScreenSeenBinding.inflate
import com.bael.dads.feature.home.screen.home.HomePresenter
import com.bael.dads.lib.data.ext.invoke
import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.data.response.Response.Empty
import com.bael.dads.lib.data.response.Response.Error
import com.bael.dads.lib.data.response.Response.Loading
import com.bael.dads.lib.data.response.Response.Success
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.ext.readDrawable
import com.bael.dads.lib.presentation.ext.readText
import com.bael.dads.lib.presentation.fragment.BaseFragment
import com.bael.dads.lib.presentation.widget.animation.empty
import com.bael.dads.lib.presentation.widget.animation.loading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import com.bael.dads.feature.home.sheet.detail.UI as DetailSheet

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseFragment<ScreenSeenBinding, Renderer, ViewModel>(),
    Renderer {
    @Inject
    lateinit var homePresenter: @JvmSuppressWildcards Lazy<HomePresenter>

    private val adapter: SeenDadJokeAdapter by lazy {
        SeenDadJokeAdapter(
            diffCallback = DadJokeDiffCallback(),
            lifecycleOwner = viewLifecycleOwner,
            onClickItemListener = { dadJoke ->
                showDetailSheet(dadJoke)
            },
            onReachEndOfItemsListener = { dadJoke ->
                viewModel().loadSeenDadJoke(
                    term = homePresenter().queryFlow.value,
                    cursor = dadJoke,
                    limit = LOAD_SEEN_LIMIT,
                    favoredOnly = viewModel().isFavoriteFilterActivated()
                )
            },
            onObserveItemListener = { dadJoke ->
                viewModel().observeDadJoke(dadJoke)
            }
        )
    }

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ScreenSeenBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded(savedInstanceState: Bundle?) {
        setupView()
        observeSearchQuery()
    }

    private fun setupView() {
        viewBinding.refreshLayout.also { layout ->
            layout.setOnRefreshListener {
                layout.isRefreshing = false

                viewModel().loadSeenDadJoke(
                    term = homePresenter().queryFlow.value,
                    cursor = null,
                    limit = LOAD_SEEN_LIMIT,
                    favoredOnly = viewModel().isFavoriteFilterActivated()
                )
            }
        }

        viewBinding.loadingStateLayout.also { layout ->
            layout.animationView.also { view ->
                view.repeatCount = INFINITE

                view.setAnimation(loading)
            }

            layout.actionButton.also { button ->
                button.visibility = GONE
            }
        }

        viewBinding.emptyStateLayout.also { layout ->
            layout.animationView.also { view ->
                view.repeatCount = INFINITE

                view.setAnimation(empty)
            }

            layout.descriptionText.also { view ->
                view.text = readText(resId = R.string.no_data_description)
            }

            layout.actionButton.also { button ->
                button.visibility = GONE
            }
        }

        viewBinding.listView.also { view ->
            view.adapter = adapter
        }
    }

    private fun observeSearchQuery() {
        lifecycleScope.launchWhenResumed {
            homePresenter().queryFlow
                .collect { query ->
                    viewModel().loadSeenDadJoke(
                        term = query,
                        cursor = null,
                        limit = LOAD_SEEN_LIMIT,
                        favoredOnly = viewModel().isFavoriteFilterActivated()
                    )
                }
        }
    }

    override fun renderSeenDadJoke(responses: List<Response<List<DadJoke>>>) {
        responses.forEach { response ->
            when (response) {
                is Loading -> renderLoadingState(responses)
                is Error -> renderErrorState()
                is Empty -> renderEmptyState(responses)
                is Success -> renderSuccessState(response)
            }
        }
    }

    override fun renderFavoriteFilter(isFavoriteFilterActivated: Boolean) {
        viewBinding.favoriteFilterButton.also { button ->
            button.setImageDrawable(
                readDrawable(
                    resId = R.drawable.ic_like.takeIf {
                        isFavoriteFilterActivated
                    } ?: R.drawable.ic_like_outline
                )
            )

            button.setOnClickListener {
                viewModel().toggleFavoriteFilter(
                    isActivated = !isFavoriteFilterActivated
                )
                viewModel().loadSeenDadJoke(
                    term = homePresenter().queryFlow.value,
                    cursor = null,
                    limit = LOAD_SEEN_LIMIT,
                    favoredOnly = !isFavoriteFilterActivated
                )

                showActivatedFavoriteFilterMessage(!isFavoriteFilterActivated)
            }
        }
    }

    private fun renderLoadingState(responses: List<Response<List<DadJoke>>>) {
        viewBinding.emptyStateLayout.also { layout ->
            layout.root.visibility = GONE
        }

        if (responses.size <= 1) {
            viewBinding.loadingStateLayout.also { layout ->
                layout.root.visibility = VISIBLE
            }
            adapter.clearData()
        } else {
            viewBinding.progressBar.also { bar ->
                bar.visibility = VISIBLE
            }
        }
    }

    private fun renderErrorState() {
        viewBinding.loadingStateLayout.also { layout ->
            layout.root.visibility = GONE
        }

        viewBinding.progressBar.also { bar ->
            bar.visibility = GONE
        }

        viewBinding.emptyStateLayout.also { layout ->
            layout.root.visibility = GONE
        }
    }

    private fun renderEmptyState(responses: List<Response<List<DadJoke>>>) {
        viewBinding.loadingStateLayout.also { layout ->
            layout.root.visibility = GONE
        }

        viewBinding.progressBar.also { bar ->
            bar.visibility = GONE
        }

        if (responses.size <= 1) {
            viewBinding.emptyStateLayout.also { layout ->
                layout.root.visibility = VISIBLE
            }
            adapter.clearData()
        }
    }

    private fun renderSuccessState(response: Success<List<DadJoke>>) {
        viewBinding.loadingStateLayout.also { layout ->
            layout.root.visibility = GONE
        }

        viewBinding.progressBar.also { bar ->
            bar.visibility = GONE
        }

        viewBinding.emptyStateLayout.also { layout ->
            layout.root.visibility = GONE
        }

        adapter.submitList(response.data)
    }

    private fun showActivatedFavoriteFilterMessage(isFavoriteFilterActivated: Boolean) {
        if (!isFavoriteFilterActivated) return
        showMessage(message = readText(resId = R.string.favorite_filter_description))
    }

    private fun showDetailSheet(dadJoke: DadJoke) {
        DetailSheet().also { sheet ->
            sheet.arguments = bundleOf("dadJoke" to dadJoke)
            sheet.onDismissListener = callback@{ _dadJoke ->
                if (dadJoke == _dadJoke) return@callback
                viewModel().favorDadJoke(dadJoke, favored = _dadJoke?.favored ?: false)
            }

            sheet.show(fragmentManager = activity?.supportFragmentManager)
        }
    }

    private companion object {
        const val LOAD_SEEN_LIMIT: Int = 10
    }
}
