package com.bael.dads.library.presentation.widget.recyclerview.adapter

import com.bael.dads.library.presentation.widget.recyclerview.adapter.cell.BaseCell

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class SingleItemAdapter<I : Any, C : BaseCell<*, I>> : BaseAdapter<I, C>() {

    override fun getItemCount(): Int = 1
}
