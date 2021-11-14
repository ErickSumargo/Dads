import Library.AndroidX.composeMaterial
import Library.AndroidX.composeUiTooling
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
}
