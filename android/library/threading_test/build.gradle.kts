import Library.Google.daggerTesting
import Library.KotlinX.coroutinesTest

plugins {
    id("androidLibrary")
}

dependencies {
    // Google
    implementation(daggerTesting)

    // KotlinX
    implementation(coroutinesTest)
}

dependencies {
    // Library
    implementation(project(":android:library:threading"))
}
