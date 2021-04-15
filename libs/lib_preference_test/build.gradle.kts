plugins {
    id("library")
}

dependencies {
    // Google
    implementation(Library.daggerTesting)
}

dependencies {
    // Lib
    implementation(project(":lib_preference"))
}
