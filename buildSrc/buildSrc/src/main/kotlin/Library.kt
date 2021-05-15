import Version.Airbnb.lottie as lottieVersion
import Version.AndroidX.appCompat as appCompatVersion
import Version.AndroidX.archTesting as archTestingVersion
import Version.AndroidX.constraintLayout as constraintLayoutVersion
import Version.AndroidX.dataStore as dataStoreVersion
import Version.AndroidX.espresso as espressoVersion
import Version.AndroidX.fragment as fragmentVersion
import Version.AndroidX.hilt as hiltVersion
import Version.AndroidX.lifecycle as lifecycleVersion
import Version.AndroidX.navigation as navigationVersion
import Version.AndroidX.recyclerView as recyclerViewVersion
import Version.AndroidX.runner as runnerVersion
import Version.AndroidX.startup as startupVersion
import Version.AndroidX.swipeRefreshLayout as swipeRefreshLayoutVersion
import Version.AndroidX.uiAutomator as uiAutomatorVersion
import Version.AndroidX.viewPager2 as viewPager2Version
import Version.AndroidX.work as workVersion
import Version.Apollo.apollo as apolloVersion
import Version.Google.autoService as autoServiceVersion
import Version.Google.dagger as daggerVersion
import Version.Google.firebaseBom as firebaseBomVersion
import Version.Google.material as materialVersion
import Version.Google.truth as truthVersion
import Version.JavaX.annotation as annotationVersion
import Version.JavaX.inject as injectVersion
import Version.KotlinX.coroutines as coroutinesVersion
import Version.KotlinX.dateTime as dateTimeVersion
import Version.Square.javaPoet as javaPoetVersion
import Version.Square.leakCanary as leakCanaryVersion
import Version.Square.sqlDelight as sqlDelightVersion

/**
 * Created by ErickSumargo on 15/04/21.
 */

object Library {

    object AndroidX {
        val appCompat: String
            get() = "androidx.appcompat:appcompat:$appCompatVersion"

        val archTesting: String
            get() = "androidx.arch.core:core-testing:$archTestingVersion"

        val constraintLayout: String
            get() = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

        val dataStore: String
            get() = "androidx.datastore:datastore-preferences:$dataStoreVersion"

        val espresso: String
            get() = "androidx.test.espresso:espresso-core:$espressoVersion"

        val fragment: String
            get() = "androidx.fragment:fragment-ktx:$fragmentVersion"

        val fragmentTesting: String
            get() = "androidx.fragment:fragment-testing:$fragmentVersion"

        val hiltCompiler: String
            get() = "androidx.hilt:hilt-compiler:$hiltVersion"

        val hiltNavigation: String
            get() = "androidx.hilt:hilt-navigation-fragment:$hiltVersion"

        val hiltWork: String
            get() = "androidx.hilt:hilt-work:$hiltVersion"

        val lifecycle: String
            get() = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"

        val lifecycleViewModelSavedState: String
            get() = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion"

        val navigationFragment: String
            get() = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"

        val navigationTesting: String
            get() = "androidx.navigation:navigation-testing:$navigationVersion"

        val navigationUi: String
            get() = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

        val recyclerView: String
            get() = "androidx.recyclerview:recyclerview:$recyclerViewVersion"

        val runner: String
            get() = "androidx.test:runner:$runnerVersion"

        val startup: String
            get() = "androidx.startup:startup-runtime:$startupVersion"

        val swipeRefreshLayout: String
            get() = "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion"

        val uiAutomator: String
            get() = "androidx.test.uiautomator:uiautomator:$uiAutomatorVersion"

        val viewPager2: String
            get() = "androidx.viewpager2:viewpager2:$viewPager2Version"

        val work: String
            get() = "androidx.work:work-runtime-ktx:$workVersion"

        val workTesting: String
            get() = "androidx.work:work-testing:$workVersion"
    }

    object Airbnb {
        val lottie: String
            get() = "com.airbnb.android:lottie:$lottieVersion"
    }

    object Apollo {
        val apolloKotlin: String
            get() = "com.apollographql.apollo:apollo-runtime-kotlin:$apolloVersion"
    }

    object Google {
        val autoService: String
            get() = "com.google.auto.service:auto-service:$autoServiceVersion"

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

        val material: String
            get() = "com.google.android.material:material:$materialVersion"

        val truth: String
            get() = "com.google.truth:truth:$truthVersion"
    }

    object JavaX {
        val annotation: String
            get() = "javax.annotation:javax.annotation-api:$annotationVersion"

        val inject: String
            get() = "javax.inject:javax.inject:$injectVersion"
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
        val javaPoet: String
            get() = "com.squareup:javapoet:$javaPoetVersion"

        val leakCanary: String
            get() = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"

        val sqlDelightAndroidDriver: String
            get() = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"

        val sqlDelightCoroutines: String
            get() = "com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion"
    }
}
