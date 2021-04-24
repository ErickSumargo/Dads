plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("app") {
            id = "app"
            implementationClass = "plugin.ApplicationPlugin"
        }

        register("domain") {
            id = "domain"
            implementationClass = "plugin.DomainPlugin"
        }

        register("feature") {
            id = "feature"
            implementationClass = "plugin.FeaturePlugin"
        }

        register("library") {
            id = "library"
            implementationClass = "plugin.LibraryPlugin"
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
    // Apollo
    implementation(Plugin.apollo)

    // Google
    implementation(Plugin.crashlytics)
    implementation(Plugin.dagger)
    implementation(Plugin.gms)
    implementation(Plugin.gradle)

    // KotlinX
    implementation(Plugin.kotlin)
    implementation(Plugin.serialization)
}

repositories {
    google()
    mavenCentral()
}
