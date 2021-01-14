package com.bael.dads.processor.generator.render

import com.bael.dads.annotation.Render
import com.bael.dads.processor.generator.BaseGenerator
import com.bael.dads.processor.generator.render.file.DefaultRendererInitializerFile
import com.bael.dads.processor.generator.render.file.RendererExecutorFile
import com.google.auto.service.AutoService
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import javax.annotation.processing.Processor
import javax.lang.model.element.Element

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AutoService(Processor::class)
internal class RenderGenerator : BaseGenerator(annotation = Render::class) {
    private lateinit var rendererClass: ClassName

    private lateinit var viewModelClass: ClassName

    private lateinit var stateClass: ClassName

    private lateinit var rendererInitializerClass: ClassName

    private lateinit var rendererDispatcherClass: ClassName

    private lateinit var rendererExecutorClass: ClassName

    override fun process(element: Element, packageName: String) {
        init(packageName)
        generate(
            generateDefaultRendererInitializerFile(element, packageName),
            generateRendererExecutorFile(element, packageName)
        )
    }

    private fun init(packageName: String) {
        rendererClass = ClassName.get(
            packageName,
            "Renderer"
        )

        viewModelClass = ClassName.get(
            packageName,
            "ViewModel"
        )

        stateClass = ClassName.get(
            packageName,
            "State"
        )

        rendererInitializerClass = ClassName.get(
            "com.bael.dads.lib.presentation.renderer",
            "RendererInitializer"
        )

        rendererDispatcherClass = ClassName.get(
            "com.bael.dads.lib.presentation.renderer",
            "RendererDispatcher"
        )

        rendererExecutorClass = ClassName.get(
            packageName,
            "RendererExecutor"
        )
    }

    private fun generateDefaultRendererInitializerFile(
        element: Element,
        packageName: String
    ): JavaFile {
        return DefaultRendererInitializerFile(
            packageName,
            rendererClass,
            viewModelClass,
            rendererInitializerClass,
            rendererExecutorClass
        ).generate()
    }

    private fun generateRendererExecutorFile(
        element: Element,
        packageName: String
    ): JavaFile {
        return RendererExecutorFile(
            annotation,
            annotationParameter = Render::state,
            element,
            packageName,
            types,
            logger,
            rendererClass,
            viewModelClass,
            stateClass,
            rendererDispatcherClass
        ).generate()
    }
}
