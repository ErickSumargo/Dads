plugins {
    id("library")
}

dependencies {
    // Google
    implementation(Library.daggerTesting)
}

dependencies {
    // Domain
    implementation(project(":domain_common"))

    // Lib
    implementation(project(":lib_remote"))
}
