apply {
    from("$rootDir/libs/lib.gradle.kts")
}

plugins {
    id("com.apollographql.apollo")
}

android {
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "JWT", "DADS_JWT")
        }

        getByName("release") {
            buildConfigField("String", "JWT", "DADS_JWT")
        }
    }
}

apollo {
    generateKotlinModels.set(true)
}

dependencies {
    // Apollo
    implementation(Library.apollo)
    implementation(Library.apolloCoroutines)

    // SquareUp
    implementation(Library.okhttp3Logging)
}

dependencies {
    // domains
    implementation(project(":domain_common"))
}
