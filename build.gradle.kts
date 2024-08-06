import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.android.application) apply(false)
    alias(libs.plugins.android.library) apply(false)
    alias(libs.plugins.firebase.crashlytics) apply(false)
    alias(libs.plugins.google.services) apply(false)
    alias(libs.plugins.kotlin.android) apply(false)
    alias(libs.plugins.kotlin.multiplatform) apply(false)
    alias(libs.plugins.compose.compiler) apply(false)
    alias(libs.plugins.jetbrains.compose) apply(false)
    alias(libs.plugins.kotlin.parcelize) apply(false)
    alias(libs.plugins.jetbrains.kotlin.serialization) apply(false)
    alias(libs.plugins.ktlint) apply(true)
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}

buildscript {
    dependencies {
        classpath(libs.gradle)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        android.set(true)
        ignoreFailures.set(false)
        reporters {
            reporter(ReporterType.JSON)
        }
        version.set("0.50.0")
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}
