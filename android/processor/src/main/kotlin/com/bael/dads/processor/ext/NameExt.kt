package com.bael.dads.processor.ext

import javax.lang.model.element.Name

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun Name.toGetter(): String {
    return toString().let { name ->
        if (name.take(2) == "is") "$name()"
        else "get${name.capitalize()}()"
    }
}
