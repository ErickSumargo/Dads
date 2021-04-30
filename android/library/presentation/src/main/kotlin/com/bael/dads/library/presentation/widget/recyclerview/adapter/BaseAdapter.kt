package com.bael.dads.library.presentation.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bael.dads.library.presentation.widget.recyclerview.adapter.cell.BaseCell

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseAdapter<I : Any, C : BaseCell<*, I>> : Adapter<C>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): C {
        val inflater = from(viewGroup.context)
        return createCell(inflater, viewGroup)
    }

    override fun onBindViewHolder(cell: C, position: Int) {
        bindCell(cell)
    }

    abstract fun createCell(inflater: LayoutInflater, viewGroup: ViewGroup): C

    abstract fun bindCell(cell: C)
}
