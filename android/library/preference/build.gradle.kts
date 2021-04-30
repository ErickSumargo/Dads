import Library.AndroidX

plugins {
    id("androidLibrary")
}

dependencies {
    // AndroidX
    implementation(AndroidX.dataStore)
}

dependencies {
    // Library
    implementation(project(":android:library:threading"))
}
