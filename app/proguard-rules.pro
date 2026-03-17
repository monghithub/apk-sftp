# ProGuard rules for APK-SFTP

# Keep JSch library classes
-keep class com.jcraft.jsch.** { *; }
-keep class com.jcraft.jsch.jce.** { *; }

# Keep Compose classes
-keep class androidx.compose.** { *; }
-keep class androidx.lifecycle.** { *; }

# Keep ViewModel classes
-keepclasseswithmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Keep our app classes
-keep class com.monghithub.apk_sftp.** { *; }
-keep interface com.monghithub.apk_sftp.** { *; }

# Keep data classes
-keepclassmembers class * {
    *** get*();
    void set*(***);
}

# Keep Kotlin metadata
-keepattributes *Annotation*,Signature,SourceFile,SourceDebugExtension
-keep class kotlin.Metadata { *; }

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
