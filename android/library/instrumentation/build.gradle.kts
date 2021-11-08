import Library.AndroidX.appCompat
import Library.AndroidX.archTesting
import Library.AndroidX.runner
import Library.AndroidX.uiAutomator
import Library.AndroidX.workTesting
import Library.Google.daggerTesting
import Library.Google.truth
import Library.KotlinX.coroutinesTest

plugins {
    id("androidLibrary")
}

dependencies {
    // AndroidX
    implementation(appCompat)
    implementation(archTesting)
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
    implementation(project(":android:library:threading"))
    implementation(project(":android:library:worker"))
}
