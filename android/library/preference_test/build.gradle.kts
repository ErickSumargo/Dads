import Library.Google.daggerTesting

plugins {
    id("androidLibrary")
}

dependencies {
    // Google
    implementation(daggerTesting)
}

dependencies {
    // Library
    implementation(project(":android:library:preference"))
}
