plugins {
    alias(libs.plugins.android.application) apply(false)
    alias(libs.plugins.android.library) apply(false)
    alias(libs.plugins.firebase.crashlytics) apply(false)
    alias(libs.plugins.google.services) apply(false)
    alias(libs.plugins.kotlin.android) apply(false)
    alias(libs.plugins.kotlin.multiplatform) apply(false)
    alias(libs.plugins.compose.compiler) apply(false)
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

buildscript {
    dependencies {
        classpath(libs.gradle)
    }
}
