import Library.Airbnb.lottie
import Library.AndroidX.composeMaterial
import Library.AndroidX.composeUiTooling
import Library.AndroidX.constraintLayout
import Library.AndroidX.fragment
import Library.AndroidX.lifecycle
import Library.AndroidX.lifecycleViewModelSavedState
import Library.AndroidX.navigationFragment
import Library.AndroidX.navigationUi
import Library.AndroidX.recyclerView
import Library.AndroidX.viewPager2
import Library.Google.material
import Version.AndroidX.compose as composeVersion

plugins {
    id("androidLibrary")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {
    // AndroidX
    implementation(composeMaterial)
    implementation(composeUiTooling)
    implementation(constraintLayout)
    implementation(fragment)
    implementation(lifecycle)
    implementation(lifecycleViewModelSavedState)
    implementation(navigationFragment)
    implementation(navigationUi)
    implementation(recyclerView)
    implementation(viewPager2)

    // Airbnb
    implementation(lottie)

    // Google
    implementation(material)
}

dependencies {
    // Shared
    implementation(project(":shared"))

    // Library
    implementation(project(":android:library:threading"))
}
