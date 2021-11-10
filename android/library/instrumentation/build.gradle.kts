import Library.AndroidX.appCompat
import Library.AndroidX.archTesting
import Library.AndroidX.composeUiTest
import Library.AndroidX.navigationCompose
import Library.AndroidX.runner
import Library.AndroidX.uiAutomator
import Library.AndroidX.workTesting
import Library.Google.daggerTesting
import Library.Google.truth
import Library.KotlinX.coroutinesTest
import Version.AndroidX.compose as composeVersion

plugins {
    id("androidLibrary")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {
    // AndroidX
    implementation(appCompat)
    implementation(archTesting)
    implementation(navigationCompose)
    implementation(composeUiTest)
    implementation(runner)
    implementation(uiAutomator)
    implementation(workTesting)

    // Google
    implementation(daggerTesting)
    implementation(truth)

    // KotlinX
    implementation(coroutinesTest)
}

dependencies {
    // Library
    implementation(project(":android:library:presentation"))
    implementation(project(":android:library:threading"))
    implementation(project(":android:library:worker"))
}
