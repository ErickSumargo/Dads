plugins {
    id("library")
}

dependencies {
    // AndroidX
    implementation(Library.dataStore)
}

dependencies {
    // Lib
    implementation(project(":lib_threading"))
}
