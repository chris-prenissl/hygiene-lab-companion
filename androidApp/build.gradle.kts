plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.christophprenissl.hygienecompanion.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.christophprenissl.hygienecompanion.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            pickFirsts += "/META-INF/DEPENDENCIES"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")

    //Compose
    implementation("androidx.compose.ui:ui:1.3.2")
    implementation("androidx.compose.ui:ui-tooling:1.3.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.2")

    //Dagger-Hilt
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.42")
    implementation("com.google.dagger:hilt-android:2.42")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.42")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    //Firebase
    implementation("com.google.gms:google-services:4.3.14")
    implementation("com.google.firebase:firebase-appdistribution-gradle:3.1.1")
    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation("com.google.firebase:firebase-firestore:24.4.1")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx:24.4.1")

    //temporary data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Excel
    implementation("org.apache.poi:poi:3.10-FINAL")
    implementation("org.apache.poi:poi-ooxml:3.10-FINAL")
    implementation("org.apache.poi:poi-ooxml-schemas:3.10-FINAL")
}
