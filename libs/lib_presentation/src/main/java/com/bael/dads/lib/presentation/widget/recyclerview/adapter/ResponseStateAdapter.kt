package com.bael.dads.lib.presentation.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bael.dads.lib.presentation.databinding.ResponseStateLayoutBinding.inflate
import com.bael.dads.lib.presentation.widget.recyclerview.adapter.cell.ResponseStateCell
import com.bael.dads.lib.presentation.widget.recyclerview.adapter.data.ResponseState

/**
 * Created by ErickSumargo on 01/01/21.
 */

class ResponseStateAdapter(
    private val state: ResponseState,
    private val onClickActionListener: ((View) -> Unit)? = null
) : SingleItemAdapter<ResponseState, ResponseStateCell>() {

    override fun createCell(
        inflater: LayoutInflater,
        viewGroup: ViewGroup
    ): ResponseStateCell {
        val viewBinding = inflate(inflater, viewGroup, false)
        return ResponseStateCell(viewBinding, onClickActionListener)
    }

    override fun bindCell(cell: ResponseStateCell) {
        cell.render(state)
    }
}
