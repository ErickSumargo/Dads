package plugin

import Application
import Library
import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

/**
 * Created by ErickSumargo on 15/04/21.
 */

class ApplicationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        applyAndroidExtension(project)

        importExternalLibs(project)
        importInternalModules(project)
    }

    private fun applyPlugins(project: Project) {
        project.plugins.apply("com.android.application")
        project.plugins.apply("com.google.gms.google-services")
        project.plugins.apply("com.google.firebase.crashlytics")
        project.plugins.apply("dagger.hilt.android.plugin")
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")
    }

    private fun applyAndroidExtension(project: Project) {
        val extension = project.extensions.getByName("android") as? AppExtension ?: return
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
                getByName("debug") {
                    applicationIdSuffix = ".debug"
                    versionNameSuffix = "-debug"

                    isMinifyEnabled = false
                    isTestCoverageEnabled = true

                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }

                getByName("release") {
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
                getByName("main").java {
                    srcDir("src/main/kotlin")
                }

                getByName("debug").java {
                    srcDir("src/debug/kotlin")
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
            }
        }
    }

    private fun importExternalLibs(project: Project) {
        project.dependencies {
            // AndroidX
            add("implementation", Library.hiltNavigation)
            add("implementation", Library.hiltWork)

            add("kapt", Library.hiltCompiler)

            add("implementation", Library.navigationFragment)
            add("implementation", Library.navigationUi)

            add("implementation", Library.work)

            // Apollo
            add("implementation", Library.apollo)
            add("implementation", Library.apolloCoroutines)

            // Google
            add("implementation", Library.dagger)

            add("kapt", Library.daggerCompiler)

            add("implementation", platform(Library.firebaseBom))
            add("implementation", Library.analytics)
            add("implementation", Library.crashlytics)

            // Square
            add("debugImplementation", Library.leakCanary)

            add("implementation", Library.okhttp3Logging)
        }
    }

    private fun importInternalModules(project: Project) {
        project.dependencies {
            // Domains
            add("implementation", project(":domain_common"))
            add("implementation", project(":domain_home"))

            // Features
            add("implementation", project(":feature_home"))

            // Libs
            add("implementation", project(":lib_database"))
            add("implementation", project(":lib_preference"))
            add("implementation", project(":lib_presentation"))
            add("implementation", project(":lib_remote"))
            add("implementation", project(":lib_threading"))
            add("implementation", project(":lib_worker"))
        }
    }
}
