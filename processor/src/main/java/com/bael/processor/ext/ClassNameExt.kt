package com.bael.dads.processor.ext

import com.squareup.javapoet.ClassName

/**
 * Created by ErickSumargo on 01/01/21.
 */

val ClassName.varName: String
    get() = simpleName().let { name ->
        name[0].toLowerCase() + name.substring(1, name.length)
    }
