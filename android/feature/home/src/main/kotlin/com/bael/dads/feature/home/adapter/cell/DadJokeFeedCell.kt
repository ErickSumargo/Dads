package com.bael.dads.feature.home.adapter.cell

import com.bael.dads.feature.home.databinding.CellFeedBinding
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.ext.toRichText
import com.bael.dads.library.presentation.widget.recyclerview.adapter.cell.BaseCell

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeFeedCell(
    viewBinding: CellFeedBinding,
    private val transitionCache: MutableMap<Int, Boolean>,
    private val onClickLikeListener: (DadJoke, Boolean) -> Unit,
    private val onClickShareListener: (DadJoke) -> Unit
) : BaseCell<CellFeedBinding, DadJoke>(viewBinding) {

    override fun render(state: DadJoke) {
        renderContent(state)
        renderSetup(state)
        renderPunchline(state)
        renderActionMenu(state)
    }

    private fun renderContent(state: DadJoke) {
        viewBinding.contentLayout.also { layout ->
            if (transitionCache[state.id] == true) {
                layout.transitionToEnd()
            } else {
                layout.transitionToStart()
            }
        }
    }

    private fun renderSetup(state: DadJoke) {
        viewBinding.setupContentLayout.also { layout ->
            layout.setOnClickListener {
                transitionCache[state.id] = true
                viewBinding.contentLayout.transitionToEnd()
            }
        }

        viewBinding.setupText.also { view ->
            view.text = state.setup.toRichText()
        }
    }

    private fun renderPunchline(state: DadJoke) {
        viewBinding.punchlineContentLayout.also { layout ->
            layout.setOnClickListener {
                transitionCache[state.id] = false
                viewBinding.contentLayout.transitionToStart()
            }
        }

        viewBinding.punchlineText.also { view ->
            view.text = state.punchline.toRichText()
        }
    }

    private fun renderActionMenu(state: DadJoke) {
        viewBinding.menuItemLayout.also { menuLayout ->
            menuLayout.favoriteLayout.also { layout ->
                if (state.favored) {
                    layout.transitionToEnd()
                } else {
                    layout.transitionToStart()
                }
            }

            menuLayout.dislikeIcon.also { view ->
                view.setOnClickListener {
                    menuLayout.favoriteLayout.transitionToEnd()
                    onClickLikeListener(state, true)
                }
            }

            menuLayout.likeIcon.also { view ->
                view.setOnClickListener {
                    menuLayout.favoriteLayout.transitionToStart()
                    onClickLikeListener(state, false)
                }
            }

            menuLayout.shareIcon.also { view ->
                view.setOnClickListener {
                    onClickShareListener(state)
                }
            }
        }
    }
}
