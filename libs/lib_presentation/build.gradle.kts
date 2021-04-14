apply {
    from("$rootDir/libs/lib.gradle.kts")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AndroidX
    implementation(Library.constraintLayout)
    implementation(Library.fragmentKtx)
    implementation(Library.lifecycle)
    implementation(Library.material)
    implementation(Library.navigationFragment)
    implementation(Library.navigationUi)
    implementation(Library.recyclerView)
    implementation(Library.viewPager2)

    // Airbnb
    implementation(Library.lottie)
}

dependencies {
    // domains
    implementation(project(":domain_common"))

    // libs
    implementation(project(":lib_threading"))
}
