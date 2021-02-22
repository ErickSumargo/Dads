package com.bael.dads.lib.presentation.widget.recyclerview.adapter.cell

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.bael.dads.lib.presentation.databinding.ItemResponseStateBinding
import com.bael.dads.lib.presentation.widget.recyclerview.adapter.data.ResponseState

/**
 * Created by ErickSumargo on 01/01/21.
 */

class ResponseStateCell(
    viewBinding: ItemResponseStateBinding,
    private val onClickActionListener: ((View) -> Unit)?
) : BaseCell<ItemResponseStateBinding, ResponseState>(viewBinding) {

    override fun render(state: ResponseState) {
        renderAnimation(state)
        renderDescription(state)
        renderAction(state)
    }

    private fun renderAnimation(state: ResponseState) {
        viewBinding.animationView.also { view ->
            if (state.animation == null) {
                view.visibility = GONE
            } else {
                view.repeatCount = INFINITE.takeIf { state.animation.playLoop } ?: 0
                view.visibility = VISIBLE

                view.setAnimation(state.animation.resource)
            }
        }
    }

    private fun renderDescription(state: ResponseState) {
        viewBinding.descriptionText.also { view ->
            if (state.description.isBlank()) {
                view.visibility = GONE
            } else {
                view.text = state.description
                view.visibility = VISIBLE
            }
        }
    }

    private fun renderAction(state: ResponseState) {
        viewBinding.actionButton.also { button ->
            if (state.actionText.isBlank() || onClickActionListener == null) {
                button.visibility = GONE
            } else {
                button.text = state.actionText
                button.visibility = VISIBLE

                button.setOnClickListener(onClickActionListener)
            }
        }
    }
}
