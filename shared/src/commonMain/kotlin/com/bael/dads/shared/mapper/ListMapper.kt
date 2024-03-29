package com.bael.dads.shared.mapper

/**
 * Created by ErickSumargo on 01/01/21.
 */

class ListMapper<I, O>(
    private val mapper: Mapper<I, O>
) : Mapper<List<I>, List<O>> {

    override fun map(data: List<I>): List<O> {
        return data.map(mapper::map)
    }
}
