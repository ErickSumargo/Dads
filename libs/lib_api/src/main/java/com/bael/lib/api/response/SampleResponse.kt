package com.bael.lib.api.response

import com.bael.lib.api.model.Sample
import com.google.gson.annotations.SerializedName

/**
 * Created by ErickSumargo on 01/01/21.
 */

data class SampleResponse(
    @SerializedName("samples")
    val samples: List<Sample>
)
