import Library.Airbnb.lottieCompose
import Library.AndroidX.constraintLayoutCompose
import Library.AndroidX.work
import Library.Google.accompanistPager
import Library.Google.accompanistSwipeRefresh

plugins {
    id("androidFeature")
}

dependencies {
    // AndroidX
    implementation(accompanistPager)
    implementation(accompanistSwipeRefresh)

    implementation(constraintLayoutCompose)

    implementation(work)

    // Airbnb
    implementation(lottieCompose)
}

dependencies {
    // Shared
    implementation(project(":shared"))

    // Data
    androidTestImplementation(project(":data:database"))
    androidTestImplementation(project(":data:database_test"))

    androidTestImplementation(project(":data:remote"))
    androidTestImplementation(project(":data:remote_test"))

    // Domain
    implementation(project(":domain:home"))

    // Library
    implementation(project(":android:library:preference"))
    androidTestImplementation(project(":android:library:preference_test"))

    implementation(project(":android:library:worker"))
}
