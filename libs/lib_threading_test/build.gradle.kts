plugins {
    id("library")
}

dependencies {
    // Google
    implementation(Library.daggerTesting)

    // KotlinX
    implementation(Library.coroutinesTest)
}

dependencies {
    // Lib
    implementation(project(":lib_threading"))
}
