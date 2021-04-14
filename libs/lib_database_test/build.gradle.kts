apply {
    from("$rootDir/libs/lib.gradle.kts")
}

dependencies {
    // AndroidX
    implementation(Library.roomKtx)

    // Google
    implementation(Library.daggerTesting)
}

dependencies {
    // libs
    implementation(project(":lib_database"))
}
