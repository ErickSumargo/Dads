import Version.Android.gradle as androidGradleVersion
import Version.Apollo.apollo as apolloVersion
import Version.Google.dagger as daggerVersion
import Version.Google.firebaseCrashlytics as firebaseCrashlyticsVersion
import Version.Google.gms as gmsVersion
import Version.KotlinX.kotlin as kotlinVersion
import Version.Square.sqlDelight as sqlDelightVersion

/**
 * Created by ErickSumargo on 15/04/21.
 */

object Plugin {

    object Android {
        val gradle: String
            get() = "com.android.tools.build:gradle:$androidGradleVersion"
    }

    object Apollo {
        val apollo: String
            get() = "com.apollographql.apollo:apollo-gradle-plugin:$apolloVersion"
    }

    object Google {
        val firebaseCrashlytics: String
            get() = "com.google.firebase:firebase-crashlytics-gradle:$firebaseCrashlyticsVersion"

        val dagger: String
            get() = "com.google.dagger:hilt-android-gradle-plugin:$daggerVersion"

        val gms: String
            get() = "com.google.gms:google-services:$gmsVersion"
    }

    object KotlinX {
        val kotlin: String
            get() = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Square {
        val sqlDelight: String
            get() = "com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion"
    }
}
