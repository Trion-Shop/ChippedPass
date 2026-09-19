# Proguard rules for DribbleMaster
-dontwarn androidx.compose.material.icons.**
-keepclassmembers class * {
    @androidx.compose.runtime.Composable *;
}
