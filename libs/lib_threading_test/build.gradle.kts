apply {
    from("$rootDir/libs/lib.gradle.kts")
}

dependencies {
    // Google
    implementation(Library.daggerTesting)

    // KotlinX
    implementation(Library.coroutinesTest)
}

dependencies {
    // libs
    implementation(project(":lib_threading"))
}
