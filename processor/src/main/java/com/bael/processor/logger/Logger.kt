package com.bael.processor.logger

import javax.annotation.processing.Messager
import javax.tools.Diagnostic.Kind.ERROR

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class Logger(messager: Messager) : Messager by messager {

    fun error(message: String) {
        printMessage(ERROR, message)
    }
}
