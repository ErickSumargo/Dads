import Library.AndroidX.appCompat
import Library.AndroidX.archTesting
import Library.AndroidX.espresso
import Library.AndroidX.fragment
import Library.AndroidX.fragmentTesting
import Library.AndroidX.navigationTesting
import Library.AndroidX.runner
import Library.Google.daggerTesting
import Library.Google.material
import Library.KotlinX.coroutinesTest

plugins {
    id("androidLibrary")
}

dependencies {
    // AndroidX
    implementation(appCompat)
    implementation(archTesting)
    implementation(espresso)
    implementation(fragment)
    implementation(fragmentTesting)
    implementation(navigationTesting)
    implementation(runner)

    // Google
    implementation(daggerTesting)
    implementation(material)

    // KotlinX
    implementation(coroutinesTest)
}

dependencies {
    // Library
    implementation(project(":android:library:presentation"))
    implementation(project(":android:library:threading"))
}
