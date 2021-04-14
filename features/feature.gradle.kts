plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")

    kotlin("android")
    kotlin("kapt")
}

apply {
    from(rootProject.file("plugins/jacoco.gradle"))
}

hilt {
    enableExperimentalClasspathAggregation = true
}

kapt {
    correctErrorTypes = true
}

android {
    compileSdkVersion(Application.compileSdk)

    buildTypes {
        getByName("debug") {
            isTestCoverageEnabled = true
        }
    }

    defaultConfig {
        minSdkVersion(Application.minSdk)
        targetSdkVersion(Application.targetSdk)
        consumerProguardFiles("proguard-rules.pro")

        testInstrumentationRunner =
            "${rootProject.applicationId}.lib.instrumentation.runner.HiltTestRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview",
            "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
            "-Xopt-in=kotlin.time.ExperimentalTime"
        )

        jvmTarget = JavaVersion.VERSION_1_8
    }

    lintOptions {
        checkReleaseBuilds = false

        error("VisibleForTests")
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    // AndroidX
    implementation(Library.dagger)
    androidTestImplementation(Library.daggerTesting)

    kapt(Library.daggerCompiler)
    kaptAndroidTest(Library.daggerCompiler)

    implementation(Library.fragmentKtx)

    implementation(Library.hiltNavigation)
    implementation(Library.hiltWork)

    kapt(Library.hiltCompiler)

    implementation(Library.navigationFragment)
    implementation(Library.navigationUi)

    // Google
    androidTestImplementation(Library.truth)
}

dependencies {
    // Internal
    implementation(project(":annotation"))
    kapt(project(":processor"))

    // libs
    implementation(project(":lib_presentation"))
    implementation(project(":lib_threading"))

    androidTestImplementation(project(":lib_instrumentation"))
    androidTestImplementation(project(":lib_threading_test"))
}
