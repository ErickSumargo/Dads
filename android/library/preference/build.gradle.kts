import Library.AndroidX.dataStore

plugins {
    id("androidLibrary")
}

dependencies {
    // AndroidX
    implementation(dataStore)
}

dependencies {
    // Library
    implementation(project(":android:library:threading"))
}
