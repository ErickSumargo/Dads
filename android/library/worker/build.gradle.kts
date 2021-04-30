import Library.AndroidX.hiltWork
import Library.AndroidX.startup
import Library.AndroidX.work

plugins {
    id("androidLibrary")
}

dependencies {
    // AndroidX
    implementation(hiltWork)
    implementation(startup)
    implementation(work)
}
