package com.bael.dads.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.bael.dads.feature.home.adapter.cell.DadJokeFeedCell
import com.bael.dads.feature.home.adapter.diffcallback.DadJokeDiffCallback
import com.bael.dads.feature.home.databinding.CellFeedBinding.inflate
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.lib.presentation.widget.recyclerview.adapter.LiveListAdapter

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeFeedAdapter(
    diffCallback: DadJokeDiffCallback,
    lifecycleOwner: LifecycleOwner,
    private val onClickLikeListener: (DadJoke, Boolean) -> Unit,
    private val onClickShareListener: (DadJoke) -> Unit,
    private val onReachEndOfItemsListener: (DadJoke) -> Unit,
    private val onObserveItemListener: suspend (DadJoke) -> Unit
) : LiveListAdapter<DadJoke, DadJokeFeedCell>(diffCallback, lifecycleOwner) {
    private val transitionCache: MutableMap<Int, Boolean> = mutableMapOf()

    override fun createCell(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): DadJokeFeedCell {
        val viewBinding = inflate(inflater, viewGroup, false)
        return DadJokeFeedCell(
            viewBinding,
            transitionCache,
            onClickLikeListener,
            onClickShareListener
        )
    }

    override fun onReachEndOfItems(item: DadJoke) {
        onReachEndOfItemsListener(item)
    }

    override fun getItemKey(item: DadJoke): Int {
        return item.id
    }

    override suspend fun observeItem(item: DadJoke) {
        onObserveItemListener(item)
    }
}
