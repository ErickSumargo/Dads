package com.bael.dads.lib.presentation.widget.recyclerview.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.bael.dads.lib.presentation.widget.recyclerview.adapter.cell.BaseCell
import kotlinx.coroutines.Job

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class LiveListAdapter<I : Any, C : BaseCell<*, I>>(
    diffCallback: ItemCallback<I>,
    private val lifecycleOwner: LifecycleOwner
) : BaseListAdapter<I, C>(diffCallback),
    LifecycleOwner by lifecycleOwner {
    private val observers: MutableMap<Any, Job> = mutableMapOf()

    override fun onViewAttachedToWindow(cell: C) {
        super.onViewAttachedToWindow(cell)

        val item = cell.itemCache
        val key = getItemKey(item)

        observers[key] = lifecycleScope.launchWhenResumed {
            observeItem(item)
        }
    }

    abstract fun getItemKey(item: I): Any

    abstract suspend fun observeItem(item: I)

    override fun onViewDetachedFromWindow(cell: C) {
        val item = cell.itemCache
        val key = getItemKey(item)

        observers[key]?.cancel()
        super.onViewDetachedFromWindow(cell)
    }
}
