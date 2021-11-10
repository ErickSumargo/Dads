import Version.Airbnb.lottieCompose as lottieComposeVersion
import Version.AndroidX.appCompat as appCompatVersion
import Version.AndroidX.archTesting as archTestingVersion
import Version.AndroidX.compose as composeVersion
import Version.AndroidX.constraintLayoutCompose as constraintLayoutComposeVersion
import Version.AndroidX.dataStore as dataStoreVersion
import Version.AndroidX.hilt as hiltVersion
import Version.AndroidX.hiltNavigationCompose as hiltNavigationComposeVersion
import Version.AndroidX.lifecycle as lifecycleVersion
import Version.AndroidX.navigation as navigationVersion
import Version.AndroidX.runner as runnerVersion
import Version.AndroidX.startup as startupVersion
import Version.AndroidX.uiAutomator as uiAutomatorVersion
import Version.AndroidX.work as workVersion
import Version.Apollo.apollo as apolloVersion
import Version.Google.accompanist as accompanistVersion
import Version.Google.dagger as daggerVersion
import Version.Google.firebaseBom as firebaseBomVersion
import Version.Google.truth as truthVersion
import Version.JavaX.annotation as annotationVersion
import Version.KotlinX.coroutines as coroutinesVersion
import Version.KotlinX.dateTime as dateTimeVersion
import Version.Square.leakCanary as leakCanaryVersion
import Version.Square.sqlDelight as sqlDelightVersion

/**
 * Created by ErickSumargo on 15/04/21.
 */

object Library {

    object AndroidX {
        val activityCompose: String
            get() = "androidx.activity:activity-compose:$composeVersion"

        val appCompat: String
            get() = "androidx.appcompat:appcompat:$appCompatVersion"

        val archTesting: String
            get() = "androidx.arch.core:core-testing:$archTestingVersion"

        val composeMaterial: String
            get() = "androidx.compose.material:material:$composeVersion"

        val composeUiTest: String
            get() = "androidx.compose.ui:ui-test-junit4:$composeVersion"

        val composeUiTooling: String
            get() = "androidx.compose.ui:ui-tooling:$composeVersion"

        val constraintLayoutCompose: String
            get() = "androidx.constraintlayout:constraintlayout-compose:$constraintLayoutComposeVersion"

        val dataStore: String
            get() = "androidx.datastore:datastore-preferences:$dataStoreVersion"

        val hiltCompiler: String
            get() = "androidx.hilt:hilt-compiler:$hiltVersion"

        val hiltNavigationCompose: String
            get() = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

        val hiltWork: String
            get() = "androidx.hilt:hilt-work:$hiltVersion"

        val lifecycle: String
            get() = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"

        val navigationCompose: String
            get() = "androidx.navigation:navigation-compose:$navigationVersion"

        val runner: String
            get() = "androidx.test:runner:$runnerVersion"

        val startup: String
            get() = "androidx.startup:startup-runtime:$startupVersion"

        val uiAutomator: String
            get() = "androidx.test.uiautomator:uiautomator:$uiAutomatorVersion"

        val work: String
            get() = "androidx.work:work-runtime-ktx:$workVersion"

        val workTesting: String
            get() = "androidx.work:work-testing:$workVersion"
    }

    object Airbnb {
        val lottieCompose: String
            get() = "com.airbnb.android:lottie-compose:$lottieComposeVersion"
    }

    object Apollo {
        val apolloKotlin: String
            get() = "com.apollographql.apollo:apollo-runtime-kotlin:$apolloVersion"
    }

    object Google {
        val accompanistInsets: String
            get() = "com.google.accompanist:accompanist-insets:$accompanistVersion"

        val accompanistPager: String
            get() = "com.google.accompanist:accompanist-pager:$accompanistVersion"

        val accompanistSwipeRefresh: String
            get() = "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion"

        val accompanistSystemUiController: String
            get() = "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"

        val dagger: String
            get() = "com.google.dagger:hilt-android:$daggerVersion"

        val daggerCompiler: String
            get() = "com.google.dagger:hilt-android-compiler:$daggerVersion"

        val daggerTesting: String
            get() = "com.google.dagger:hilt-android-testing:$daggerVersion"

        val firebaseAnalytics: String
            get() = "com.google.firebase:firebase-analytics-ktx"

        val firebaseBom: String
            get() = "com.google.firebase:firebase-bom:$firebaseBomVersion"

        val firebaseCrashlytics: String
            get() = "com.google.firebase:firebase-crashlytics-ktx"

        val truth: String
            get() = "com.google.truth:truth:$truthVersion"
    }

    object JavaX {
        val annotation: String
            get() = "javax.annotation:javax.annotation-api:$annotationVersion"
    }

    object KotlinX {
        val coroutines: String
            get() = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"

        val coroutinesAndroid: String
            get() = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"

        val coroutinesTest: String
            get() = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion"

        val dateTime: String
            get() = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"
    }

    object Square {
        val leakCanary: String
            get() = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"

        val sqlDelightAndroidDriver: String
            get() = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"

        val sqlDelightCoroutines: String
            get() = "com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion"
    }
}
