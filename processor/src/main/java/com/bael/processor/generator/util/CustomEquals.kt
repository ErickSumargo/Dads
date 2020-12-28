package com.bael.dads.processor.generator.util

import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterSpec
import com.squareup.javapoet.TypeName.BOOLEAN
import com.squareup.javapoet.TypeName.DOUBLE
import com.squareup.javapoet.TypeName.FLOAT
import com.squareup.javapoet.TypeName.OBJECT
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal object CustomEquals {
    private const val FIRST_NAME: String = "first"
    private const val SECOND_NAME: String = "second"

    private const val EQUALS_METHOD_NAME: String = "equals"

    internal fun generateDoubleEqualsMethod(): MethodSpec {
        val firstParameter = ParameterSpec.builder(DOUBLE, FIRST_NAME)
            .build()

        val secondParameter = ParameterSpec.builder(DOUBLE, SECOND_NAME)
            .build()

        val statement = CodeBlock.builder()
            .addStatement("return Double.compare(\$1N, \$2N) == 0", FIRST_NAME, SECOND_NAME)
            .build()

        return MethodSpec.methodBuilder(EQUALS_METHOD_NAME)
            .addModifiers(PRIVATE, FINAL)
            .addParameter(firstParameter)
            .addParameter(secondParameter)
            .addCode(statement)
            .returns(BOOLEAN)
            .build()
    }

    internal fun generateFloatEqualsMethod(): MethodSpec {
        val firstParameter = ParameterSpec.builder(FLOAT, FIRST_NAME)
            .build()

        val secondParameter = ParameterSpec.builder(FLOAT, SECOND_NAME)
            .build()

        val statement = CodeBlock.builder()
            .addStatement("return Float.compare(\$1N, \$2N) == 0", FIRST_NAME, SECOND_NAME)
            .build()

        return MethodSpec.methodBuilder(EQUALS_METHOD_NAME)
            .addModifiers(PRIVATE, FINAL)
            .addParameter(firstParameter)
            .addParameter(secondParameter)
            .addCode(statement)
            .returns(BOOLEAN)
            .build()
    }

    internal fun generateObjectEqualsMethod(): MethodSpec {
        val firstParameter = ParameterSpec.builder(OBJECT, FIRST_NAME)
            .build()

        val secondParameter = ParameterSpec.builder(OBJECT, SECOND_NAME)
            .build()

        val statement = CodeBlock.builder()
            .addStatement(
                "return \$1N == null ? \$2N == null : \$1N.equals(\$2N)",
                FIRST_NAME,
                SECOND_NAME
            )
            .build()

        return MethodSpec.methodBuilder(EQUALS_METHOD_NAME)
            .addModifiers(PRIVATE, FINAL)
            .addParameter(firstParameter)
            .addParameter(secondParameter)
            .addCode(statement)
            .returns(BOOLEAN)
            .build()
    }
}
