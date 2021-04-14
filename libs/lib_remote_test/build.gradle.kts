apply {
    from("$rootDir/libs/lib.gradle.kts")
}

dependencies {
    // Google
    implementation(Library.daggerTesting)
}

dependencies {
    // domains
    implementation(project(":domain_common"))

    // libs
    implementation(project(":lib_remote"))
}
