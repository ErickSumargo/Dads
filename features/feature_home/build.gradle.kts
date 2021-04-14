apply {
    from("$rootDir/features/feature.gradle.kts")
}

dependencies {
    // AndroidX
    implementation(Library.constraintLayout)
    implementation(Library.swipeRefreshLayout)
    implementation(Library.viewPager2)
    implementation(Library.work)

    // Airbnb
    implementation(Library.lottie)
}

dependencies {
    // domains
    implementation(project(":domain_common"))
    implementation(project(":domain_home"))

    // libs
    implementation(project(":lib_preference"))
    implementation(project(":lib_worker"))

    androidTestImplementation(project(":lib_database"))
    androidTestImplementation(project(":lib_database_test"))

    androidTestImplementation(project(":lib_remote"))
    androidTestImplementation(project(":lib_remote_test"))
}
