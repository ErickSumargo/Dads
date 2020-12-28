package com.bael.processor.generator

import com.bael.processor.logger.Logger
import com.squareup.javapoet.JavaFile
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.SourceVersion.latest
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Types
import kotlin.reflect.KClass

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal abstract class BaseGenerator(
    protected val annotation: KClass<out Annotation>
) : AbstractProcessor() {
    private lateinit var filer: Filer

    protected lateinit var types: Types

    protected lateinit var logger: Logger

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)

        filer = processingEnv.filer
        types = processingEnv.typeUtils
        logger = Logger(processingEnv.messager)
    }

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(annotation.java.name)

    override fun getSupportedSourceVersion(): SourceVersion = latest()

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        for (element in roundEnv.getElementsAnnotatedWith(annotation.java)) {
            val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
            process(element, packageName)
        }
        return true
    }

    protected abstract fun process(element: Element, packageName: String)

    protected fun generate(vararg files: JavaFile) {
        files.forEach { file ->
            file.writeTo(filer)
        }
    }
}
