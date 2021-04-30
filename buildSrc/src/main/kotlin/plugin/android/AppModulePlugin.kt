@file:Suppress("UnstableApiUsage")

package plugin.android

import Application
import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by ErickSumargo on 15/04/21.
 */

class AppModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)

        applyAppExtension(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply {
            apply("com.android.application")
            apply("com.google.gms.google-services")
            apply("com.google.firebase.crashlytics")
            apply("dagger.hilt.android.plugin")
            apply("kotlin-android")
            apply("kotlin-kapt")
        }
    }

    private fun applyAppExtension(project: Project) {
        val extension = project.extensions.getByName("android")
                as? AppExtension ?: return
        extension.apply {
            compileSdkVersion(Application.compileSdk)

            defaultConfig {
                applicationId = Application.id

                versionCode(Application.versionCode)
                versionName(Application.versionName)

                targetSdkVersion(Application.targetSdk)
                minSdkVersion(Application.minSdk)

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                named("debug") {
                    applicationIdSuffix = ".debug"
                    versionNameSuffix = "-debug"

                    isMinifyEnabled = false
                    isTestCoverageEnabled = true

                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }

                named("release") {
                    isDebuggable = false
                    isMinifyEnabled = true
                    isShrinkResources = true

                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            sourceSets {
                named("main") {
                    java {
                        srcDir("src/main/kotlin")
                    }
                }

                named("debug") {
                    java {
                        srcDir("src/debug/kotlin")
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
            }

            packagingOptions {
                exclude("META-INF/AL2.0")
                exclude("META-INF/LGPL2.1")
                exclude("META-INF/*.kotlin_module")
            }
        }
    }
}
