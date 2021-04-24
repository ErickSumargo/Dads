package plugin

import Application
import Library
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Created by ErickSumargo on 15/04/21.
 */

class DomainPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)

        applyKotlinMultiplatformExtension(project)
        applyLibraryExtension(project)

        importExternalLibs(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply {
            apply("com.android.library")
            apply("dagger.hilt.android.plugin")
            apply("kotlin-android")
            apply("kotlin-kapt")
            apply("kotlin-multiplatform")
        }
    }

    private fun applyKotlinMultiplatformExtension(project: Project) {
        val extension = project.extensions.getByName("kotlin")
                as? KotlinMultiplatformExtension ?: return
        extension.apply {
            android()

            sourceSets {
                val commonMain by getting {
                    dependencies {
                        // KotlinX
                        implementation(Library.coroutines)
                    }
                }

                val androidMain by getting {
                    dependencies {
                        // Google
                        implementation(Library.dagger)
                    }
                }
            }
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
                    manifest {
                        srcFile("src/androidMain/AndroidManifest.xml")
                    }

                    java {
                        srcDirs("src/main/kotlin")
                    }
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

            packagingOptions {
                exclude("DebugProbesKt.bin")
            }
        }
    }

    private fun importExternalLibs(project: Project) {
        project.dependencies {
            // Google
            add("kapt", Library.daggerCompiler)
        }
    }
}
