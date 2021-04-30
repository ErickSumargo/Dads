plugins {
    id("domain")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Shared
                implementation(project(":shared"))

                // Data
                implementation(project(":data:database"))
                implementation(project(":data:remote"))
            }
        }

        val androidMain by getting
    }
}
