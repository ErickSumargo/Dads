@file:Suppress("UnstableApiUsage")

package plugin.android

import Application
import Library.AndroidX.fragment
import Library.AndroidX.hiltCompiler
import Library.AndroidX.hiltNavigation
import Library.AndroidX.hiltWork
import Library.AndroidX.navigationFragment
import Library.AndroidX.navigationUi
import Library.Google.dagger
import Library.Google.daggerCompiler
import Library.Google.daggerTesting
import Library.Google.truth
import com.android.build.gradle.LibraryExtension
import dagger.hilt.android.plugin.HiltExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import plugin.test.JacocoTestReportPlugin

/**
 * Created by ErickSumargo on 15/04/21.
 */

class FeatureModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)

        applyFeatureExtension(project)
        applyHiltExtension(project)
        applyKaptExtension(project)

        configureKotlinCompiler(project)

        importExternalLibs(project)
        importInternalModules(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply {
            apply("com.android.library")
            apply("dagger.hilt.android.plugin")
            apply("kotlin-android")
            apply("kotlin-kapt")

            apply(JacocoTestReportPlugin::class)
        }
    }

    private fun applyFeatureExtension(project: Project) {
        val extension = project.extensions.getByName("android")
                as? LibraryExtension ?: return
        extension.apply {
            compileSdkVersion(Application.compileSdk)

            defaultConfig {
                targetSdkVersion(Application.targetSdk)
                minSdkVersion(Application.minSdk)

                consumerProguardFiles("proguard-rules.pro")

                testInstrumentationRunner =
                    "${Application.id}.library.instrumentation.runner.HiltTestRunner"
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

                named("androidTest") {
                    java {
                        srcDirs("src/androidTest/kotlin")
                    }
                }
            }

            buildFeatures.apply {
                viewBinding = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            lintOptions {
                isCheckReleaseBuilds = false

                error("VisibleForTests")
            }

            packagingOptions {
                exclude("META-INF/AL2.0")
                exclude("META-INF/LGPL2.1")
                exclude("META-INF/*.kotlin_module")
            }
        }
    }

    private fun applyHiltExtension(project: Project) {
        val extension = project.extensions.getByName("hilt")
                as? HiltExtension ?: return
        extension.apply {
            enableExperimentalClasspathAggregation = true
        }
    }

    private fun applyKaptExtension(project: Project) {
        val extension = project.extensions.getByName("kapt")
                as? KaptExtension ?: return
        extension.apply {
            correctErrorTypes = true
        }
    }

    private fun configureKotlinCompiler(project: Project) {
        project.tasks.withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs += listOf(
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
            // AndroidX
            add("implementation", fragment)

            add("implementation", hiltNavigation)
            add("implementation", hiltWork)
            add("kapt", hiltCompiler)

            add("implementation", navigationFragment)
            add("implementation", navigationUi)

            // Google
            add("implementation", dagger)
            add("androidTestImplementation", daggerTesting)
            add("kapt", daggerCompiler)
            add("kaptAndroidTest", daggerCompiler)

            add("androidTestImplementation", truth)
        }
    }

    private fun importInternalModules(project: Project) {
        project.dependencies {
            // Internal
            add("implementation", project(":android:annotation"))
            add("kapt", project(":android:processor"))

            // Library
            add("implementation", project(":android:library:presentation"))
            add("implementation", project(":android:library:threading"))

            add("androidTestImplementation", project(":android:library:instrumentation"))
            add("androidTestImplementation", project(":android:library:threading_test"))
        }
    }
}
