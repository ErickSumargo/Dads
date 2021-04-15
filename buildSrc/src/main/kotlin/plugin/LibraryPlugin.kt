package plugin

import Application
import Library
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Created by ErickSumargo on 15/04/21.
 */

class LibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        applyLibraryExtension(project)

        importExternalLibs(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply("com.android.library")
        project.plugins.apply("dagger.hilt.android.plugin")
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")
    }

    private fun applyLibraryExtension(project: Project) {
        val extension = project.extensions.getByName("android") as? LibraryExtension ?: return
        extension.apply {
            compileSdkVersion(Application.compileSdk)

            defaultConfig {
                targetSdkVersion(Application.targetSdk)
                minSdkVersion(Application.minSdk)

                consumerProguardFiles("proguard-rules.pro")
            }

            buildTypes {
                getByName("debug") {
                    isTestCoverageEnabled = true
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            project.tasks.withType<KotlinCompile>().configureEach {
                kotlinOptions {
                    freeCompilerArgs = listOf(
                        "-Xallow-result-return-type",
                        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-Xopt-in=kotlinx.coroutines.FlowPreview",
                        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi"
                    )

                    jvmTarget = "${JavaVersion.VERSION_1_8}"
                }
            }

            lintOptions {
                error("VisibleForTests")
            }
        }
    }

    private fun importExternalLibs(project: Project) {
        project.dependencies {
            // Google
            add("implementation", Library.dagger)

            add("kapt", Library.daggerCompiler)

            // KotlinX
            add("implementation", Library.coroutines)
        }
    }
}
