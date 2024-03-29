package plugin.android

import Application
import Library.Google.dagger
import Library.Google.daggerCompiler
import Library.KotlinX.coroutinesAndroid
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

class LibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)

        applyLibraryExtension(project)

        configureKotlinCompiler(project)

        importExternalLibs(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply {
            apply("com.android.library")
            apply("dagger.hilt.android.plugin")
            apply("kotlin-android")
            apply("kotlin-kapt")
        }
    }

    private fun applyLibraryExtension(project: Project) {
        val extension = project.extensions.getByName("android")
                as? LibraryExtension ?: return
        extension.apply {
            compileSdkVersion(Application.compileSdk)

            defaultConfig {
                targetSdkVersion(Application.targetSdk)
                minSdkVersion(Application.minSdk)

                consumerProguardFiles("proguard-rules.pro")
            }

            buildTypes {
                named("debug") {
                    isTestCoverageEnabled = true
                }
            }

            sourceSets {
                named("main") {
                    java {
                        srcDirs("src/main/kotlin")
                    }
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            lintOptions {
                error("VisibleForTests")
            }
        }
    }

    private fun configureKotlinCompiler(project: Project) {
        project.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs += listOf(
                    "-Xallow-result-return-type",
                    "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xopt-in=kotlinx.coroutines.FlowPreview",
                    "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi"
                )
                jvmTarget = "${JavaVersion.VERSION_1_8}"
            }
        }
    }

    private fun importExternalLibs(project: Project) {
        project.dependencies {
            // Google
            add("implementation", dagger)
            add("kapt", daggerCompiler)

            // KotlinX
            add("implementation", coroutinesAndroid)
        }
    }
}
