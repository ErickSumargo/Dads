apply {
    from("$rootDir/libs/lib.gradle.kts")
}

dependencies {
    // AndroidX
    implementation(Library.hiltWork)
    implementation(Library.startup)
    implementation(Library.work)
}
