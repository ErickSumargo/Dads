/**
 * Created by ErickSumargo on 15/04/21.
 */

object Library {
    // AndroidX
    val appCompat: String
        get() = "androidx.appcompat:appcompat:${Version.appCompat}"

    val archTesting: String
        get() = "androidx.arch.core:core-testing:${Version.archTesting}"

    val constraintLayout: String
        get() = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"

    val dataStore: String
        get() = "androidx.datastore:datastore-preferences:${Version.dataStore}"

    val espresso: String
        get() = "androidx.test.espresso:espresso-core:${Version.espresso}"

    val fragmentKtx: String
        get() = "androidx.fragment:fragment-ktx:${Version.fragment}"

    val fragmentTesting: String
        get() = "androidx.fragment:fragment-testing:${Version.fragment}"

    val hiltCompiler: String
        get() = "androidx.hilt:hilt-compiler:${Version.hilt}"

    val hiltNavigation: String
        get() = "androidx.hilt:hilt-navigation-fragment:${Version.hilt}"

    val hiltWork: String
        get() = "androidx.hilt:hilt-work:${Version.hilt}"

    val lifecycle: String
        get() = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"

    val navigationFragment: String
        get() = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"

    val navigationTesting: String
        get() = "androidx.navigation:navigation-testing:${Version.navigation}"

    val navigationUi: String
        get() = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

    val recyclerView: String
        get() = "androidx.recyclerview:recyclerview:${Version.recyclerView}"

    val room: String
        get() = "androidx.room:room-runtime:${Version.room}"

    val roomCompiler: String
        get() = "androidx.room:room-compiler:${Version.room}"

    val roomKtx: String
        get() = "androidx.room:room-ktx:${Version.room}"

    val runner: String
        get() = "androidx.test:runner:${Version.runner}"

    val startup: String
        get() = "androidx.startup:startup-runtime:${Version.startup}"

    val swipeRefreshLayout: String
        get() = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"

    val viewPager2: String
        get() = "androidx.viewpager2:viewpager2:${Version.viewPager2}"

    val work: String
        get() = "androidx.work:work-runtime-ktx:${Version.work}"

    // Airbnb
    val lottie: String
        get() = "com.airbnb.android:lottie:${Version.lottie}"

    // Apollo
    val apollo: String
        get() = "com.apollographql.apollo:apollo-runtime:${Version.apollo}"

    val apolloCoroutines: String
        get() = "com.apollographql.apollo:apollo-coroutines-support:${Version.apollo}"

    // Google
    val autoService: String
        get() = "com.google.auto.service:auto-service:${Version.autoService}"

    val analytics: String
        get() = "com.google.firebase:firebase-analytics-ktx"

    val crashlytics: String
        get() = "com.google.firebase:firebase-crashlytics-ktx"

    val dagger: String
        get() = "com.google.dagger:hilt-android:${Version.dagger}"

    val daggerCompiler: String
        get() = "com.google.dagger:hilt-android-compiler:${Version.dagger}"

    val daggerTesting: String
        get() = "com.google.dagger:hilt-android-testing:${Version.dagger}"

    val firebaseBom: String
        get() = "com.google.firebase:firebase-bom:${Version.firebaseBom}"

    val material: String
        get() = "com.google.android.material:material:${Version.material}"

    val truth: String
        get() = "com.google.truth:truth:${Version.truth}"

    // JavaX
    val inject: String
        get() = "javax.inject:javax.inject:${Version.inject}"

    // KotlinX
    val coroutines: String
        get() = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"

    val coroutinesAndroid: String
        get() = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    val coroutinesTest: String
        get() = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"

    // Square
    val javaPoet: String
        get() = "com.squareup:javapoet:${Version.javaPoet}"

    val leakCanary: String
        get() = "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"

    val okhttp3Logging: String
        get() = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp3Logging}"
}
