package com.bael.dads.processor.generator.render

import com.bael.dads.annotation.RenderWith
import com.bael.dads.processor.generator.BaseGenerator
import com.bael.dads.processor.generator.render.file.DefaultRendererInitializerFile
import com.bael.dads.processor.generator.render.file.RenderExecutorFile
import com.google.auto.service.AutoService
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import javax.annotation.processing.Processor
import javax.lang.model.element.Element

/**
 * Created by ErickSumargo on 01/01/21.
 */

@AutoService(Processor::class)
internal class RenderGenerator : BaseGenerator(annotation = RenderWith::class) {
    private lateinit var rendererClass: ClassName

    private lateinit var viewModelClass: ClassName

    private lateinit var stateClass: ClassName

    private lateinit var rendererInitializerClass: ClassName

    private lateinit var baseRenderExecutorClass: ClassName

    private lateinit var renderExecutorClass: ClassName

    override fun process(element: Element, packageName: String) {
        init(packageName)
        generate(
            generateDefaultRendererInitializerFile(element, packageName),
            generateRenderExecutorFile(element, packageName)
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

        baseRenderExecutorClass = ClassName.get(
            "com.bael.dads.lib.presentation.renderer",
            "BaseRenderExecutor"
        )

        renderExecutorClass = ClassName.get(
            packageName,
            "RenderExecutor"
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
            renderExecutorClass
        ).generate()
    }

    private fun generateRenderExecutorFile(
        element: Element,
        packageName: String
    ): JavaFile {
        return RenderExecutorFile(
            annotation,
            annotationParameter = RenderWith::state,
            element,
            packageName,
            types,
            logger,
            rendererClass,
            viewModelClass,
            stateClass,
            baseRenderExecutorClass
        ).generate()
    }
}
