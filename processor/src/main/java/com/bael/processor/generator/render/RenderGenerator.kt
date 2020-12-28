package com.bael.dads.processor.generator.render

import com.bael.dads.annotation.Render
import com.bael.dads.processor.generator.BaseGenerator
import com.bael.dads.processor.generator.render.file.ComponentRendererFile
import com.bael.dads.processor.generator.render.file.RendererFile
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
    private lateinit var componentClass: ClassName

    private lateinit var viewModelClass: ClassName

    private lateinit var stateClass: ClassName

    private lateinit var rendererFactoryClass: ClassName

    private lateinit var baseRendererClass: ClassName

    private lateinit var componentRendererClass: ClassName

    override fun process(element: Element, packageName: String) {
        init(packageName)
        generate(
            generateRendererFile(element, packageName),
            generateComponentRendererFile(element, packageName)
        )
    }

    private fun init(packageName: String) {
        componentClass = ClassName.get(
            packageName,
            "Component"
        )

        viewModelClass = ClassName.get(
            packageName,
            "ViewModel"
        )

        stateClass = ClassName.get(
            packageName,
            "State"
        )

        rendererFactoryClass = ClassName.get(
            "com.bael.dads.lib.presentation.renderer",
            "RendererFactory"
        )

        baseRendererClass = ClassName.get(
            "com.bael.dads.lib.presentation.renderer",
            "BaseRenderer"
        )

        componentRendererClass = ClassName.get(
            packageName,
            "Component_Renderer"
        )
    }

    private fun generateRendererFile(element: Element, packageName: String): JavaFile {
        return RendererFile(
            packageName,
            componentClass,
            viewModelClass,
            rendererFactoryClass,
            componentRendererClass
        ).generate()
    }

    private fun generateComponentRendererFile(element: Element, packageName: String): JavaFile {
        return ComponentRendererFile(
            annotation,
            annotationParameter = Render::state,
            element,
            packageName,
            types,
            logger,
            componentClass,
            viewModelClass,
            stateClass,
            baseRendererClass
        ).generate()
    }
}
