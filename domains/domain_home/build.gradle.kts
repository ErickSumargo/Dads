plugins {
    id("domain")
}

dependencies {
    // Domain
    implementation(project(":domain_common"))

    // Lib
    implementation(project(":lib_database"))
    implementation(project(":lib_remote"))
}
