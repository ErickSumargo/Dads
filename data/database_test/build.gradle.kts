import Library.Google.daggerTesting
import Library.Square.sqlDelightAndroidDriver

plugins {
    id("data")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Data
                implementation(project(":data:database"))
            }
        }

        val androidMain by getting {
            dependencies {
                // Google
                implementation(daggerTesting)

                // Square
                implementation(sqlDelightAndroidDriver)
            }
        }
    }
}
