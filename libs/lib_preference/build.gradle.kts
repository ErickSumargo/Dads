apply {
    from("$rootDir/libs/lib.gradle.kts")
}

dependencies {
    // AndroidX
    implementation(Library.dataStore)
}

dependencies {
    // libs
    implementation(project(":lib_threading"))
}
