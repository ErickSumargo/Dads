package com.bael.dads.lib.api.response

import com.bael.dads.lib.api.model.DadJoke

/**
 * Created by ErickSumargo on 01/01/21.
 */

data class DadJokesResponse(
    val dadJokes: List<DadJoke>,
    val page: Int
)