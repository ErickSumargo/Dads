plugins {
    id("library")
}

dependencies {
    // AndroidX
    implementation(Library.appCompat)
    implementation(Library.archTesting)
    implementation(Library.coroutinesTest)
    implementation(Library.espresso)
    implementation(Library.fragmentKtx)
    implementation(Library.fragmentTesting)
    implementation(Library.runner)
    implementation(Library.material)
    implementation(Library.navigationTesting)

    // Google
    implementation(Library.daggerTesting)
}

dependencies {
    // Lib
    implementation(project(":lib_navigation"))
    implementation(project(":lib_presentation"))
    implementation(project(":lib_threading"))
}
