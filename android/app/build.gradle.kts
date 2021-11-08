import Library.AndroidX.activityCompose
import Library.AndroidX.composeMaterial
import Library.AndroidX.composeUiTooling
import Library.AndroidX.hiltCompiler
import Library.AndroidX.hiltNavigation
import Library.AndroidX.hiltWork
import Library.AndroidX.navigationCompose
import Library.AndroidX.navigationFragment
import Library.AndroidX.navigationUi
import Library.AndroidX.work
import Library.Apollo.apolloKotlin
import Library.Google.accompanistInsets
import Library.Google.accompanistPager
import Library.Google.accompanistSystemUiController
import Library.Google.dagger
import Library.Google.daggerCompiler
import Library.Google.firebaseBom
import Library.Square.leakCanary
import Library.Google.firebaseAnalytics as analytics
import Library.Google.firebaseCrashlytics as crashlytics

plugins {
    id("androidApp")
}

dependencies {
    // AndroidX
    implementation(accompanistInsets)
    implementation(accompanistPager)
    implementation(accompanistSystemUiController)
    implementation(activityCompose)

    implementation(composeMaterial)
    implementation(composeUiTooling)

    implementation(hiltNavigation)
    implementation(hiltWork)
    kapt(hiltCompiler)

    implementation(navigationCompose)
    implementation(navigationFragment)
    implementation(navigationUi)

    implementation(work)

    // Apollo
    implementation(apolloKotlin)

    // Google
    implementation(dagger)
    kapt(daggerCompiler)

    implementation(platform(firebaseBom))
    implementation(analytics)
    implementation(crashlytics)

    // Square
    debugImplementation(leakCanary)
}

dependencies {
    // Shared
    implementation(project(":shared"))

    // Data
    implementation(project(":data:database"))
    implementation(project(":data:remote"))

    // Domain
    implementation(project(":domain:home"))
    implementation(project(":shared"))

    // Feature
    implementation(project(":android:feature:home"))

    // Library
    implementation(project(":android:library:preference"))
    implementation(project(":android:library:presentation"))
    implementation(project(":android:library:threading"))
    implementation(project(":android:library:worker"))
}
