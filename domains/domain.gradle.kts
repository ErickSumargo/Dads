plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")

    kotlin("android")
    kotlin("kapt")
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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
                "-Xallow-result-return-type",
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi"
        )

        jvmTarget = JavaVersion.VERSION_1_8
    }

    lintOptions {
        error("VisibleForTests")
    }
}

dependencies {
    // KotlinX
    implementation(Library.coroutines)

    // Google
    implementation(Library.dagger)

    kapt(Library.daggerCompiler)
}
