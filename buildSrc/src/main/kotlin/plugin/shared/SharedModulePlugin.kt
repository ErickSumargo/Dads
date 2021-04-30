package plugin.shared

import Application
import Library.Google.dagger
import Library.Google.daggerCompiler
import Library.JavaX.annotation
import Library.KotlinX.coroutines
import Library.KotlinX.dateTime
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Created by ErickSumargo on 01/05/21.
 */

open class SharedModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)

        applyKotlinMultiplatformExtension(project)
        applyAndroidLibraryExtension(project)

        configureKotlinCompiler(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply {
            apply("kotlin-multiplatform")
            apply("kotlin-kapt")
            apply("com.android.library")
            apply("dagger.hilt.android.plugin")
        }
    }

    private fun applyKotlinMultiplatformExtension(project: Project) {
        val extension = project.extensions.getByName("kotlin")
                as? KotlinMultiplatformExtension ?: return
        extension.apply {
            android()

            sourceSets {
                all {
                    languageSettings.apply {
                        enableLanguageFeature("InlineClasses")
                        enableLanguageFeature("AllowResultInReturnType")

                        useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                        useExperimentalAnnotation("kotlinx.coroutines.FlowPreview")
                        useExperimentalAnnotation("kotlinx.coroutines.InternalCoroutinesApi")
                    }
                }

                val commonMain by getting {
                    dependencies {
                        // KotlinX
                        implementation(coroutines)
                        implementation(dateTime)
                    }
                }

                val androidMain by getting {
                    dependencies {
                        // Google
                        implementation(dagger)

                        project.configurations["kapt"].dependencies.add(
                            project.dependencies.create(daggerCompiler)
                        )

                        // JavaX
                        compileOnly(annotation)
                    }
                }
            }
        }
    }

    private fun applyAndroidLibraryExtension(project: Project) {
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
                        srcDirs("src/androidMain/kotlin")
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

            packagingOptions {
                exclude("DebugProbesKt.bin")
            }
        }
    }

    private fun configureKotlinCompiler(project: Project) {
        project.tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "${JavaVersion.VERSION_1_8}"
            }
        }
    }
}
