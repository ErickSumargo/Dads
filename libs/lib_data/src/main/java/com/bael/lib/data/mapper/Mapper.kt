package com.bael.lib.data.mapper

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface Mapper<I, O> {

    fun map(data: I): O
}
