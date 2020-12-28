package com.bael.processor.generator.render.file

import com.bael.processor.ext.varName
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeSpec
import org.jetbrains.annotations.NotNull
import javax.inject.Inject
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class RendererFile(
    private val packageName: String,
    private val componentClass: ClassName,
    private val viewModelClass: ClassName,
    private val rendererFactoryClass: ClassName,
    private val componentRendererClass: ClassName
) {

    fun generate(): JavaFile {
        val `class` = generateClass()
        return JavaFile.builder(packageName, `class`).build()
    }

    private fun generateClass(): TypeSpec {
        val constructor = generateConstructor()

        val superInterface = generateSuperInterface()

        val instanceField = generateInstanceField()

        val initMethod = generateInitMethod()

        return TypeSpec.classBuilder("Renderer")
            .addModifiers(PUBLIC, FINAL)
            .addMethod(constructor)
            .addSuperinterface(superInterface)
            .addField(instanceField)
            .addMethod(initMethod)
            .build()
    }

    private fun generateConstructor(): MethodSpec {
        return MethodSpec.constructorBuilder()
            .addModifiers(PUBLIC)
            .addAnnotation(Inject::class.java)
            .build()
    }

    private fun generateSuperInterface(): ParameterizedTypeName {
        return ParameterizedTypeName.get(
            rendererFactoryClass,
            componentClass,
            viewModelClass
        )
    }

    private fun generateInstanceField(): FieldSpec {
        return FieldSpec.builder(
            componentRendererClass,
            INSTANCE_NAME,
            PRIVATE
        ).build()
    }

    private fun generateInitMethod(): MethodSpec {
        val componentParameter = ParameterSpec.builder(componentClass, componentClass.varName)
            .addAnnotation(NotNull::class.java)
            .build()

        val viewModelParameter = ParameterSpec.builder(viewModelClass, viewModelClass.varName)
            .addAnnotation(NotNull::class.java)
            .build()

        val statement = CodeBlock.builder()
            .addStatement(
                "this.\$1N = new \$2N(\$3N, \$4N)",
                INSTANCE_NAME,
                componentRendererClass.simpleName(),
                componentParameter.name,
                viewModelParameter.name
            )
            .addStatement(
                "this.\$1N.observeState()",
                INSTANCE_NAME
            )
            .build()

        return MethodSpec.methodBuilder("init")
            .addAnnotation(Override::class.java)
            .addModifiers(PUBLIC)
            .addParameter(componentParameter)
            .addParameter(viewModelParameter)
            .addCode(statement)
            .build()
    }

    companion object {
        private const val INSTANCE_NAME: String = "INSTANCE"
    }
}
