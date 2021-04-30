import Library.Square.sqlDelightAndroidDriver
import Library.Square.sqlDelightCoroutines

plugins {
    id("data")
    id("com.squareup.sqldelight")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Square
                implementation(sqlDelightCoroutines)
            }

            dependencies {
                // Shared
                implementation(project(":shared"))
            }
        }

        val androidMain by getting {
            dependencies {
                // Square
                implementation(sqlDelightAndroidDriver)
            }
        }
    }
}

sqldelight {
    database("DadsDatabase") {
        packageName = "${Application.id}.data.database"
    }
}
