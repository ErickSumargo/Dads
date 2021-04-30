package com.bael.dads.shared.mapper

/**
 * Created by ErickSumargo on 01/01/21.
 */

class ResultMapper<I, O>(
    private val mapper: Mapper<I, O>
) : Mapper<Result<I>, Result<O>> {

    override fun map(data: Result<I>): Result<O> {
        return data.map(mapper::map)
    }
}
