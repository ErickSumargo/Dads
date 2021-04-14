plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("dagger.hilt.android.plugin")

    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Application.compileSdk)

    defaultConfig {
        applicationId = Application.id
        minSdkVersion(Application.minSdk)
        targetSdkVersion(Application.targetSdk)

        versionCode(Application.versionCode)
        versionName(Application.versionName)

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

    buildFeatures {
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

dependencies {
    // AndroidX
    implementation(Library.hiltNavigation)
    implementation(Library.hiltWork)

    kapt(Library.hiltCompiler)

    implementation(Library.navigationFragment)
    implementation(Library.navigationUi)

    implementation(Library.work)

    // Apollo
    implementation(Library.apollo)
    implementation(Library.apolloCoroutines)

    // Google
    implementation(Library.dagger)

    kapt(Library.daggerCompiler)

    implementation(platform(Library.firebaseBom))
    implementation(Library.crashlytics)

    // SquareUp
    debugImplementation(Library.leakCanary)

    implementation(Library.okhttp3Logging)
}

dependencies {
    // features
    implementation(project(":feature_home"))

    // domains
    implementation(project(":domain_common"))
    implementation(project(":domain_home"))

    // libs
    implementation(project(":lib_database"))
    implementation(project(":lib_preference"))
    implementation(project(":lib_presentation"))
    implementation(project(":lib_remote"))
    implementation(project(":lib_threading"))
    implementation(project(":lib_worker"))
}
