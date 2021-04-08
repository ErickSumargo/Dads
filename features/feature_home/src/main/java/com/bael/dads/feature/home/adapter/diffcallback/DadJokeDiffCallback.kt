package com.bael.dads.feature.home.adapter.diffcallback

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.bael.dads.domain.home.model.DadJoke

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeDiffCallback : ItemCallback<DadJoke>() {

    override fun areItemsTheSame(
        oldItem: DadJoke,
        newItem: DadJoke
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DadJoke,
        newItem: DadJoke
    ): Boolean {
        return oldItem == newItem
    }
}
