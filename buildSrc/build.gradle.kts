import Plugin.Apollo.apollo
import Plugin.Google.dagger
import Plugin.Google.firebaseCrashlytics
import Plugin.Google.gms
import Plugin.KotlinX.serialization
import Plugin.Square.sqlDelight
import Plugin.Android.gradle as androidGradle
import Plugin.KotlinX.kotlin as kotlinGradle

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("shared") {
            id = "shared"
            implementationClass = "plugin.shared.SharedModulePlugin"
        }

        register("data") {
            id = "data"
            implementationClass = "plugin.shared.DataModulePlugin"
        }

        register("domain") {
            id = "domain"
            implementationClass = "plugin.shared.DomainModulePlugin"
        }

        register("androidApp") {
            id = "androidApp"
            implementationClass = "plugin.android.AppModulePlugin"
        }

        register("androidFeature") {
            id = "androidFeature"
            implementationClass = "plugin.android.FeatureModulePlugin"
        }

        register("androidLibrary") {
            id = "androidLibrary"
            implementationClass = "plugin.android.LibraryModulePlugin"
        }
    }
}

kotlin {
    sourceSets {
        named("main") {
            kotlin.apply {
                srcDir("buildSrc/src/main/kotlin")
            }
        }
    }
}

dependencies {
    // Android
    implementation(androidGradle)

    // Apollo
    implementation(apollo)

    // Google
    implementation(firebaseCrashlytics)
    implementation(dagger)
    implementation(gms)

    // KotlinX
    implementation(kotlinGradle)
    implementation(serialization)

    // Square
    implementation(sqlDelight)
}

repositories {
    google()
    mavenCentral()
    maven {
        setUrl("https://www.jetbrains.com/intellij-repository/releases")
        setUrl("https://jetbrains.bintray.com/intellij-third-party-dependencies")
    }
}
