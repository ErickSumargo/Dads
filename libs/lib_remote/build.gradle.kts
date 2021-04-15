plugins {
    id("library")
    id("com.apollographql.apollo")
    id("com.google.secrets_gradle_plugin") version Version.secrets
}

apollo {
    generateKotlinModels.set(true)
}

secrets {
    propertiesFileName = "keys.properties"
}

dependencies {
    // Apollo
    implementation(Library.apollo)
    implementation(Library.apolloCoroutines)

    // Square
    implementation(Library.okhttp3Logging)
}

dependencies {
    // Domain
    implementation(project(":domain_common"))
}
