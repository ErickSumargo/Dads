package com.bael.dads.library.presentation.widget.recyclerview.adapter.data


/**
 * Created by ErickSumargo on 01/01/21.
 */

data class ResponseState(
    val animation: AnimationProperty? = null,
    val description: String = "",
    val actionText: String = ""
) {
    data class AnimationProperty(
        val resource: String,
        val playLoop: Boolean
    )
}
