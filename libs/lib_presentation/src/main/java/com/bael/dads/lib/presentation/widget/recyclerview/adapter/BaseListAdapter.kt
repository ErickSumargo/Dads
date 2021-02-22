package com.bael.dads.lib.presentation.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import com.bael.dads.lib.presentation.widget.recyclerview.adapter.cell.BaseCell

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseListAdapter<I : Any, C : BaseCell<*, I>>(
    diffCallback: ItemCallback<I>
) : ListAdapter<I, C>(diffCallback) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): C {
        val inflater = from(viewGroup.context)
        return createCell(inflater, viewGroup)
    }

    override fun onBindViewHolder(cell: C, position: Int) {
        val item = getItem(position)

        cell.cacheItem(item)
        cell.render(item)

        if (position < itemCount - 1) return
        onReachEndOfItems(item)
    }

    abstract fun createCell(inflater: LayoutInflater, viewGroup: ViewGroup): C

    abstract fun onReachEndOfItems(item: I)

    fun getItemAt(position: Int): I? {
        return if (position < 0 || position >= itemCount) null
        else getItem(position)
    }

    fun lastItem(): I? {
        return getItemAt(position = itemCount - 1)
    }

    fun clearData() {
        submitList(listOf())
    }
}
