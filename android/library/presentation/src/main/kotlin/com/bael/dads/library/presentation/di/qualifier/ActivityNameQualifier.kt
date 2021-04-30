package com.bael.dads.library.presentation.di.qualifier

import javax.inject.Qualifier

/**
 * Created by stef_ang on 24/04/21.
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ActivityNameQualifier(val name: String) {

    companion object {
        const val ACTIVITY_MAIN: String = "main"
    }
}
