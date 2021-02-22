package com.bael.dads.feature.home.sheet.detail

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bael.dads.feature.home.databinding.SheetDetailBinding
import com.bael.dads.feature.home.databinding.SheetDetailBinding.inflate
import com.bael.dads.lib.data.ext.invoke
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
    private val dadJoke: DadJoke? by lazy {
        arguments?.getSerializable("dadJoke") as? DadJoke
    }

    var onDismissListener: ((DadJoke?, Boolean) -> Unit)? = null

    override val fullHeight: Boolean = true

    override fun createView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SheetDetailBinding {
        return inflate(inflater, container, false)
    }

    override suspend fun onViewLoaded() {
        setupView()
        viewModel().setDadJokeFavored(favored = dadJoke?.favored ?: false)
    }

    private fun setupView() {
        viewBinding.setupText.also { view ->
            view.text = dadJoke?.setup.toRichText()
        }

        viewBinding.punchlineText.also { view ->
            view.text = dadJoke?.punchline.toRichText()
        }

        viewBinding.menuItemLayout.also { menuLayout ->
            menuLayout.dislikeIcon.also { icon ->
                icon.setOnClickListener {
                    viewModel().setDadJokeFavored(favored = true)
                }
            }

            menuLayout.likeIcon.also { icon ->
                icon.setOnClickListener {
                    viewModel().setDadJokeFavored(favored = false)
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

    override fun renderFavorMenu(dadJokeFavored: Boolean) {
        viewBinding.menuItemLayout.also { menuLayout ->
            menuLayout.favoriteLayout.also { layout ->
                if (!dadJokeFavored) {
                    layout.transitionToStart()
                } else {
                    layout.transitionToEnd()
                }
            }
        }
    }

    private fun showSharePreviewSheet(dadJoke: DadJoke?) {
        dadJoke ?: return

        SharePreviewSheet().also { sheet ->
            sheet.arguments = bundleOf("dadJoke" to dadJoke)
            sheet.show(fragmentManager = activity?.supportFragmentManager)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismissListener?.invoke(dadJoke, viewModel().isDadJokeFavored())
        super.onDismiss(dialog)
    }
}
