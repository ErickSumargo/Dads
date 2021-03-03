package com.bael.dads.feature.home.sheet.detail

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bael.dads.feature.home.databinding.SheetDetailBinding
import com.bael.dads.feature.home.databinding.SheetDetailBinding.inflate
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.presentation.ext.toRichText
import com.bael.dads.lib.presentation.sheet.BaseSheet
import dagger.hilt.android.AndroidEntryPoint
import com.bael.dads.feature.home.sheet.sharepreview.UI as SharePreviewSheet

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AndroidEntryPoint
internal class UI :
    BaseSheet<SheetDetailBinding, Renderer, ViewModel>(),
    Renderer {
    var onDismissListener: ((DadJoke?) -> Unit)? = null

    override val fullHeight: Boolean = true

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SheetDetailBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded() {
        viewModel.receiveDadJoke()
    }

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
        SharePreviewSheet().also { sheet ->
            sheet.arguments = bundleOf("dadJoke" to dadJoke)
            sheet.show(fragmentManager = activity?.supportFragmentManager)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismissListener?.invoke(viewModel.dadJoke)
        super.onDismiss(dialog)
    }
}
