package com.bael.annotation

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Target(CLASS)
@Retention(SOURCE)
@MustBeDocumented
annotation class Render(val state: KClass<*>)
