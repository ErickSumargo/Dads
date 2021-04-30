# ProGuard Configuration file
#
# See http://proguard.sourceforge.net/index.html#manual/usage.html

# Firebase Crashlytics
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keep class com.google.firebase.crashlytics.** { *; }
-dontwarn com.google.firebase.crashlytics.**

# Jetpack - Navigation Component
-keep class androidx.navigation.fragment.NavHostFragment

# KotlinX - Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.bael.dads.**$$serializer { *; }
-keepclassmembers class com.bael.dads.** {
    *** Companion;
}
-keepclasseswithmembers class com.bael.dads.** {
    kotlinx.serialization.KSerializer serializer(...);
}
