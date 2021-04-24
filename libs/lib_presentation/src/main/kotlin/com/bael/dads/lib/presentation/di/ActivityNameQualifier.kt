package com.bael.dads.lib.presentation.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ActivityNameQualifier(val name: String) {

    companion object {
        const val ACTIVITY_MAIN = "main"
    }
}
