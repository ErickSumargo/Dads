/**
 * Created by ErickSumargo on 15/04/21.
 */

object Plugin {
    // Apollo
    val apollo: String
        get() = "com.apollographql.apollo:apollo-gradle-plugin:${Version.apollo}"

    // Google
    val crashlytics: String
        get() = "com.google.firebase:firebase-crashlytics-gradle:${Version.crashlytics}"

    val dagger: String
        get() = "com.google.dagger:hilt-android-gradle-plugin:${Version.dagger}"

    val gms: String
        get() = "com.google.gms:google-services:${Version.gms}"

    val gradle: String
        get() = "com.android.tools.build:gradle:${Version.gradle}"

    // KotlinX
    val kotlin: String
        get() = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

    val serialization: String
        get() = "org.jetbrains.kotlin:kotlin-serialization:${Version.kotlin}"
}
