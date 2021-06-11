import Library.Google.firebaseBom
import Library.Google.firebaseDatabase as database

plugins {
    id("data")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Shared
                implementation(project(":shared"))
            }
        }

        val androidMain by getting {
            dependencies {
                // Google
                implementation(project.dependencies.platform(firebaseBom))
                implementation(database)
            }
        }
    }
}
