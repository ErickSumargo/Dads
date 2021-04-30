import Library.Google.autoService
import Library.JavaX.inject
import Library.Square.javaPoet

plugins {
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    // Google
    implementation(autoService)
    kapt(autoService)

    // JavaX
    implementation(inject)

    // Square
    implementation(javaPoet)
}

dependencies {
    // Internal
    implementation(project(":android:annotation"))
}
