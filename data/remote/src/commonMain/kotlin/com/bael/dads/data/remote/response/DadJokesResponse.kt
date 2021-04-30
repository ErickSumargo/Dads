package com.bael.dads.data.remote.response

import com.bael.dads.data.remote.model.DadJoke

/**
 * Created by ErickSumargo on 01/01/21.
 */

data class DadJokesResponse(
    val dadJokes: List<DadJoke>,
    val cursor: String?
)
