package com.bael.lib.data.exception

import java.io.IOException

/**
 * Created by ErickSumargo on 01/01/21.
 */

class NoNetworkException : IOException() {
    override val message: String
        get() = "No Network Access"
}
