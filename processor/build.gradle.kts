plugins {
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    // Google
    implementation(Library.autoService)

    kapt(Library.autoService)

    // JavaX
    implementation(Library.inject)

    // Square
    implementation(Library.javaPoet)
}

dependencies {
    // Internal
    implementation(project(":annotation"))
}
