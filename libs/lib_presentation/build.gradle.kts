plugins {
    id("library")
}

android {
    buildFeatures.apply {
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
    // Domain
    implementation(project(":domain_common"))

    // Lib
    implementation(project(":lib_threading"))
}
