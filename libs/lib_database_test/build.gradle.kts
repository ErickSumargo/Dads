plugins {
    id("library")
}

dependencies {
    // AndroidX
    implementation(Library.roomKtx)

    // Google
    implementation(Library.daggerTesting)
}

dependencies {
    // Lib
    implementation(project(":lib_database"))
}
