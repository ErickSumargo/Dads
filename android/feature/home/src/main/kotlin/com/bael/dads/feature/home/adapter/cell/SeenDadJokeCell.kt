package com.bael.dads.feature.home.adapter.cell

import com.bael.dads.feature.home.databinding.CellSeenBinding
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.library.presentation.ext.toRichText
import com.bael.dads.library.presentation.widget.recyclerview.adapter.cell.BaseCell

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class SeenDadJokeCell(
    viewBinding: CellSeenBinding,
    private val cellHeight: Int,
    private val onClickItemListener: (DadJoke) -> Unit
) : BaseCell<CellSeenBinding, DadJoke>(viewBinding) {

    override fun render(state: DadJoke) {
        renderContent(state)
        renderSetup(state)
    }

    private fun renderContent(state: DadJoke) {
        viewBinding.contentLayout.also { layout ->
            layout.minHeight = cellHeight

            layout.setOnClickListener {
                onClickItemListener(state)
            }
        }
    }

    private fun renderSetup(state: DadJoke) {
        viewBinding.setupText.also { view ->
            view.text = state.setup.toRichText()
        }
    }
}
