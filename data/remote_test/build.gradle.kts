import Library.Google.daggerTesting

plugins {
    id("data")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Shared
                implementation(project(":shared"))

                // Data
                implementation(project(":data:remote"))
            }
        }

        val androidMain by getting {
            dependencies {
                // Google
                implementation(daggerTesting)
            }
        }
    }
}
