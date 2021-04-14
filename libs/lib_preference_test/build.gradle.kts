apply {
    from("$rootDir/libs/lib.gradle.kts")
}

dependencies {
    // Google
    implementation(Library.daggerTesting)
}

dependencies {
    // libs
    implementation(project(":lib_preference"))
}
