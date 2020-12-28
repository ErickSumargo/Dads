package com.bael.dads.processor.ext

import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Types
import kotlin.reflect.KProperty

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun AnnotationMirror.toElement(): Element {
    return annotationType.asElement()
}

fun AnnotationMirror.parameterOf(typeUtils: Types, parameter: KProperty<*>): TypeElement? {
    val typeMirror = elementValues?.entries?.firstOrNull { (element, _) ->
        element.simpleName.toString() == parameter.name
    }?.value?.value as? TypeMirror
    return typeUtils.asElement(typeMirror) as TypeElement
}
