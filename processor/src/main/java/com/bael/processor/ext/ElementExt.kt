package com.bael.processor.ext

import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import kotlin.reflect.KClass

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun Element.annotationOf(annotation: KClass<*>): AnnotationMirror? {
    return annotationMirrors.find { annotationMirror ->
        val typeElement = annotationMirror.toElement() as TypeElement
        typeElement.qualifiedName.toString() == annotation.java.name
    }
}

fun Element.members(kind: ElementKind): List<Element> {
    return enclosedElements?.filter { element ->
        element.kind == kind
    }.orEmpty()
}

val Element.isNullable: Boolean
    get() = annotationMirrors.any { annotationMirror ->
        annotationMirror.toElement().simpleName.endsWith("Nullable")
    }
