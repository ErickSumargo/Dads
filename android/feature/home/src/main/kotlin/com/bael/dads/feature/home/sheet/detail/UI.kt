package com.bael.dads.feature.home.sheet.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.feature.home.databinding.SheetDetailBinding
import com.bael.dads.feature.home.databinding.SheetDetailBinding.inflate
import com.bael.dads.library.presentation.ext.toRichText
import com.bael.dads.library.presentation.sheet.BaseSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseSheet<SheetDetailBinding, Renderer, Event, ViewModel>(),
    Renderer {
    override val fullHeight: Boolean = true

    override val viewModel: ViewModel by viewModels()

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SheetDetailBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded(savedInstanceState: Bundle?) {
        viewModel.receiveDadJoke()
    }

    override suspend fun action(event: Event) {}

    override fun renderDetail(dadJoke: DadJoke?) {
        dadJoke ?: return

        viewBinding.setupText.also { view ->
            view.text = dadJoke.setup.toRichText()
        }

        viewBinding.punchlineText.also { view ->
            view.text = dadJoke.punchline.toRichText()
        }

        viewBinding.menuItemLayout.also { menuLayout ->
            menuLayout.favoriteLayout.also { layout ->
                if (!dadJoke.favored) {
                    layout.transitionToStart()
                } else {
                    layout.transitionToEnd()
                }
            }

            menuLayout.dislikeIcon.also { icon ->
                icon.setOnClickListener {
                    viewModel.favorDadJoke(favored = true)
                }
            }

            menuLayout.likeIcon.also { icon ->
                icon.setOnClickListener {
                    viewModel.favorDadJoke(favored = false)
                }
            }

            menuLayout.shareIcon.also { icon ->
                icon.setOnClickListener {
                    showSharePreviewSheet(dadJoke)
                    dismiss()
                }
            }
        }
    }

    private fun showSharePreviewSheet(dadJoke: DadJoke) {
        val direction = UIDirections.showSharePreviewSheet(dadJoke)
        navigate(direction)
    }

    override fun onDismiss(dialog: DialogInterface) {
        setFragmentResult(
            requestKey = "detailSheet",
            result = bundleOf("dadJoke" to viewModel.dadJoke)
        )
        super.onDismiss(dialog)
    }
}
