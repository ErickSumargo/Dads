package plugin

import Application
import Library
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

/**
 * Created by ErickSumargo on 15/04/21.
 */

class FeaturePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        applyFeatureExtension(project)
        applyHiltExtension(project)
        applyKaptExtension(project)

        importExternalLibs(project)
        importInternalModules(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply("com.android.library")
        project.plugins.apply("dagger.hilt.android.plugin")
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")

        project.plugins.apply(JacocoTestReportPlugin::class)
    }

    private fun applyFeatureExtension(project: Project) {
        val extension = project.extensions.getByName("android") as? LibraryExtension ?: return
        extension.apply {
            compileSdkVersion(Application.compileSdk)

            defaultConfig {
                targetSdkVersion(Application.targetSdk)
                minSdkVersion(Application.minSdk)

                consumerProguardFiles("proguard-rules.pro")

                testInstrumentationRunner =
                    "${Application.id}.lib.instrumentation.runner.HiltTestRunner"
            }

            buildTypes {
                getByName("debug") {
                    isTestCoverageEnabled = true
                }
            }

            sourceSets {
                getByName("main").java {
                    srcDirs("src/main/kotlin")
                }

                getByName("androidTest").java {
                    srcDirs("src/androidTest/kotlin")
                }
            }

            buildFeatures.apply {
                viewBinding = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            project.tasks.withType<KotlinCompile>().configureEach {
                kotlinOptions {
                    freeCompilerArgs = listOf(
                        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-Xopt-in=kotlinx.coroutines.FlowPreview",
                        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
                        "-Xopt-in=kotlin.time.ExperimentalTime"
                    )

                    jvmTarget = "${JavaVersion.VERSION_1_8}"
                }
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
        val extension = project.extensions.getByName("hilt") as? HiltExtension ?: return
        extension.apply {
            enableExperimentalClasspathAggregation = true
        }
    }

    private fun applyKaptExtension(project: Project) {
        val extension = project.extensions.getByName("kapt") as? KaptExtension ?: return
        extension.apply {
            correctErrorTypes = true
        }
    }

    private fun importExternalLibs(project: Project) {
        project.dependencies {
            // AndroidX
            add("implementation", Library.fragmentKtx)

            add("implementation", Library.hiltNavigation)
            add("implementation", Library.hiltWork)

            add("kapt", Library.hiltCompiler)

            add("implementation", Library.navigationFragment)
            add("implementation", Library.navigationUi)

            // Google
            add("implementation", Library.dagger)
            add("androidTestImplementation", Library.daggerTesting)

            add("kapt", Library.daggerCompiler)
            add("kaptAndroidTest", Library.daggerCompiler)

            add("androidTestImplementation", Library.truth)
        }
    }

    private fun importInternalModules(project: Project) {
        project.dependencies {
            // Internal
            add("implementation", project(":annotation"))
            add("kapt", project(":processor"))

            // Lib
            add("implementation", project(":lib_presentation"))
            add("implementation", project(":lib_threading"))

            add("androidTestImplementation", project(":lib_instrumentation"))
            add("androidTestImplementation", project(":lib_threading_test"))
        }
    }
}
