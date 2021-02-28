package com.bael.dads.processor.generator.render.file

import com.bael.dads.processor.ext.annotationOf
import com.bael.dads.processor.ext.fields
import com.bael.dads.processor.ext.isNullable
import com.bael.dads.processor.ext.methods
import com.bael.dads.processor.ext.parameterOf
import com.bael.dads.processor.ext.toGetter
import com.bael.dads.processor.ext.varName
import com.bael.dads.processor.generator.util.CustomEquals.generateDoubleEqualsMethod
import com.bael.dads.processor.generator.util.CustomEquals.generateFloatEqualsMethod
import com.bael.dads.processor.generator.util.CustomEquals.generateObjectEqualsMethod
import com.bael.dads.processor.logger.Logger
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Name
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.TypeKind.ARRAY
import javax.lang.model.type.TypeKind.DOUBLE
import javax.lang.model.type.TypeKind.FLOAT
import javax.lang.model.util.Types
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class RenderExecutorFile(
    private val annotation: KClass<out Annotation>,
    private val annotationParameter: KProperty<*>,
    private val element: Element,
    private val packageName: String,
    private val types: Types,
    private val logger: Logger,
    private val rendererClass: ClassName,
    private val viewModelClass: ClassName,
    private val stateClass: ClassName,
    private val baseRenderExecutorClass: ClassName
) {

    fun generate(): JavaFile {
        val `class` = generateClass()
        return JavaFile.builder(packageName, `class`).build()
    }

    private fun generateClass(): TypeSpec {
        val superClass = generateSuperClass()

        val rendererField = generateRendererField()

        val viewModelField = generateViewModelField()

        val constructor = generateConstructor()

        val renderMethod = generateRenderMethod()

        val doubleEqualsMethod = generateDoubleEqualsMethod()

        val floatEqualsMethod = generateFloatEqualsMethod()

        val objectEqualsMethod = generateObjectEqualsMethod()

        return TypeSpec.classBuilder("RenderExecutor")
            .addModifiers(PUBLIC, FINAL)
            .superclass(superClass)
            .addField(rendererField)
            .addField(viewModelField)
            .addMethod(constructor)
            .addMethod(renderMethod)
            .addMethod(doubleEqualsMethod)
            .addMethod(floatEqualsMethod)
            .addMethod(objectEqualsMethod)
            .build()
    }

    private fun generateSuperClass(): TypeName {
        return ParameterizedTypeName.get(
            baseRenderExecutorClass,
            stateClass
        )
    }

    private fun generateRendererField(): FieldSpec {
        return FieldSpec.builder(rendererClass, rendererClass.varName)
            .addModifiers(PRIVATE, FINAL)
            .build()
    }

    private fun generateViewModelField(): FieldSpec {
        return FieldSpec.builder(viewModelClass, viewModelClass.varName)
            .addModifiers(PRIVATE, FINAL)
            .build()
    }

    private fun generateConstructor(): MethodSpec {
        val rendererParameter = ParameterSpec.builder(rendererClass, rendererClass.varName)
            .addAnnotation(NotNull::class.java)
            .build()

        val viewModelParameter = ParameterSpec.builder(viewModelClass, viewModelClass.varName)
            .addAnnotation(NotNull::class.java)
            .build()

        val statement = CodeBlock.builder()
            .addStatement("super(\$1N, \$2N)", rendererParameter.name, viewModelParameter.name)
            .addStatement("this.\$1N = \$1N", rendererParameter.name)
            .addStatement("this.\$1N = \$1N", viewModelParameter.name)
            .build()

        return MethodSpec.constructorBuilder()
            .addModifiers(PUBLIC)
            .addParameter(rendererParameter)
            .addParameter(viewModelParameter)
            .addCode(statement)
            .build()
    }

    private fun generateRenderMethod(): MethodSpec {
        val stateFields = element.annotationOf(annotation)
            ?.parameterOf(types, parameter = annotationParameter)
            ?.fields
            .orEmpty()

        val renderers = (element as TypeElement).methods

        val stateNullifiedStatement = generateStateNullifiedStatement(
            stateFields,
            renderers
        )

        val stateDiffStatement = generateStateDiffStatement(
            stateFields,
            renderers
        )

        val oldStateParameter = ParameterSpec.builder(stateClass, OLD_STATE_NAME)
            .addAnnotation(Nullable::class.java)
            .build()

        val newStateParameter = ParameterSpec.builder(stateClass, NEW_STATE_NAME)
            .addAnnotation(NotNull::class.java)
            .build()

        return MethodSpec.methodBuilder("render")
            .addAnnotation(Override::class.java)
            .addModifiers(PUBLIC, FINAL)
            .addParameter(oldStateParameter)
            .addParameter(newStateParameter)
            .addCode(stateNullifiedStatement)
            .addCode(stateDiffStatement)
            .build()
    }

    private fun generateStateNullifiedStatement(
        stateFields: Map<Name, TypeName>,
        renderers: List<ExecutableElement>
    ): CodeBlock {
        return CodeBlock.builder()
            .beginControlFlow("if (\$1N == null)", OLD_STATE_NAME).apply {
                renderers.forEach { renderer ->
                    val statement = generateRenderStatement(
                        renderer,
                        stateFields
                    )
                    addStatement(statement)
                }
            }
            .endControlFlow()
            .build()
    }

    private fun generateStateDiffStatement(
        stateFields: Map<Name, TypeName>,
        renderers: List<ExecutableElement>
    ): CodeBlock {
        return CodeBlock.builder()
            .beginControlFlow("else").apply {
                renderers.forEach next@{ renderer ->
                    if (renderer.parameters.isEmpty()) return@next

                    beginControlFlow(
                        "if (\$N)",
                        generateStateDiffCheck(renderer.parameters)
                    )

                    val statement = generateRenderStatement(
                        renderer,
                        stateFields
                    )
                    addStatement(statement)

                    endControlFlow()
                }
            }
            .endControlFlow()
            .build()
    }

    private fun generateStateDiffCheck(parameters: List<VariableElement>): String {
        return parameters.joinToString(" || ") { parameter ->
            val state = parameter.simpleName.toGetter()
            val type = parameter.asType().kind

            val customEquals = type in listOf(FLOAT, DOUBLE, ARRAY)
            if (type.isPrimitive && !customEquals) {
                "$OLD_STATE_NAME.$state != $NEW_STATE_NAME.$state"
            } else if (parameter.isNullable || customEquals) {
                "!equals($OLD_STATE_NAME.$state, $NEW_STATE_NAME.$state)"
            } else {
                "!$OLD_STATE_NAME.$state.equals($NEW_STATE_NAME.$state)"
            }
        }
    }

    private fun generateRenderStatement(
        renderer: ExecutableElement,
        stateFields: Map<Name, TypeName>,
    ): CodeBlock {
        return CodeBlock.of(
            "renderer.\$1N(\$2N)",
            renderer.simpleName,
            renderer.parameters.joinToString { parameter ->
                val name = parameter.simpleName
                stateFields[name] ?: logger.error("Field $name not found in State")

                "$NEW_STATE_NAME.${name.toGetter()}"
            }
        )
    }

    companion object {
        private const val OLD_STATE_NAME: String = "oldState"
        private const val NEW_STATE_NAME: String = "newState"
    }
}
