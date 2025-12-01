# Add project specific ProGuard rules here.
-keep class dev.rakamin.newsapp.model.** { *; }
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn okhttp3.**
-dontwarn retrofit2.**
