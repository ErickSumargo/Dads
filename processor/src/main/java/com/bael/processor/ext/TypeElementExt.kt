package com.bael.processor.ext

import com.squareup.javapoet.TypeName
import javax.lang.model.element.ElementKind.FIELD
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Name
import javax.lang.model.element.TypeElement

/**
 * Created by ErickSumargo on 01/01/21.
 */

val TypeElement.fields: Map<Name, TypeName>
    get() = members(kind = FIELD).associate { field ->
        field.simpleName to TypeName.get(field.asType())
    }

val TypeElement.methods: List<ExecutableElement>
    get() = enclosedElements.map { element ->
        val method = element as? ExecutableElement
        method ?: return emptyList()
        method
    }
