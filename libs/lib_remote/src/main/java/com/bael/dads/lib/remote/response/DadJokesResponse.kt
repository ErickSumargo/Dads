package com.bael.dads.lib.remote.response

import com.bael.dads.lib.remote.model.DadJoke

/**
 * Created by ErickSumargo on 01/01/21.
 */

data class DadJokesResponse(
    val dadJokes: List<DadJoke>,
    val cursor: String?
)
