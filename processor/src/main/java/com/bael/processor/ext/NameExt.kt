package com.bael.processor.ext

import javax.lang.model.element.Name

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun Name.toGetter(): String {
    return "get${toString().capitalize()}()"
}
