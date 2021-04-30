import Library.Apollo.apolloKotlin

plugins {
    id("data")
    id("com.apollographql.apollo")
    id("com.google.secrets_gradle_plugin") version Version.Google.secrets
}

kotlin {
    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("com.apollographql.apollo.api.ApolloExperimental")
            }
        }

        val commonMain by getting {
            dependencies {
                // Apollo
                implementation(apolloKotlin)
            }

            dependencies {
                // Shared
                implementation(project(":shared"))
            }
        }

        val androidMain by getting
    }
}

apollo {
    generateKotlinModels.set(true)
}

secrets {
    propertiesFileName = "keys.properties"
}
