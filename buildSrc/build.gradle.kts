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

dependencies {
    implementation("com.android.tools.build:gradle:4.1.1")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.31-alpha")
    implementation("com.google.gms:google-services:4.3.5")
    implementation("com.google.firebase:firebase-crashlytics-gradle:2.5.0")
    implementation("com.apollographql.apollo:apollo-gradle-plugin:2.4.5")

    implementation(kotlin("gradle-plugin", "1.4.0"))
}

repositories {
    google()
    mavenCentral()
}
