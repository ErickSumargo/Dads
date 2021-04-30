import Library.Airbnb
import Library.AndroidX
import Library.Google

plugins {
    id("androidLibrary")
}

android {
    buildFeatures.apply {
        viewBinding = true
    }
}

dependencies {
    // AndroidX
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.fragment)
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUi)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.viewPager2)

    // Airbnb
    implementation(Airbnb.lottie)

    // Google
    implementation(Google.material)
}

dependencies {
    // Shared
    implementation(project(":shared"))

    // Library
    implementation(project(":android:library:threading"))
}
