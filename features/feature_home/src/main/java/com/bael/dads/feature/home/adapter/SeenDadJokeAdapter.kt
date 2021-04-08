package com.bael.dads.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.bael.dads.feature.home.adapter.cell.SeenDadJokeCell
import com.bael.dads.feature.home.adapter.diffcallback.DadJokeDiffCallback
import com.bael.dads.feature.home.databinding.CellSeenBinding.inflate
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.lib.presentation.widget.recyclerview.adapter.LiveListAdapter

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class SeenDadJokeAdapter(
    diffCallback: DadJokeDiffCallback,
    lifecycleOwner: LifecycleOwner,
    private val onClickItemListener: (DadJoke) -> Unit,
    private val onReachEndOfItemsListener: (DadJoke) -> Unit,
    private val onObserveItemListener: suspend (DadJoke) -> Unit
) : LiveListAdapter<DadJoke, SeenDadJokeCell>(diffCallback, lifecycleOwner) {

    override fun createCell(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): SeenDadJokeCell {
        val viewBinding = inflate(inflater, viewGroup, false)
        val cellHeight = viewGroup.height / 2

        return SeenDadJokeCell(viewBinding, cellHeight, onClickItemListener)
    }

    override fun getItemKey(item: DadJoke): Int {
        return item.id
    }

    override fun onReachEndOfItems(item: DadJoke) {
        onReachEndOfItemsListener(item)
    }

    override suspend fun observeItem(item: DadJoke) {
        onObserveItemListener(item)
    }
}
