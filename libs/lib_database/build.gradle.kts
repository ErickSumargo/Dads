plugins {
    id("library")
}

android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "${project.projectDir}/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }
}

dependencies {
    // AndroidX
    implementation(Library.room)
    implementation(Library.roomKtx)

    kapt(Library.roomCompiler)
}
