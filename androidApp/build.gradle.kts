plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.christophprenissl.hygienecompanion.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.christophprenissl.hygienecompanion.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    packaging {
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

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Compose
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    implementation("androidx.compose.runtime:runtime:1.4.3")

    //Koin
    implementation("io.insert-koin:koin-android:3.2.0")
    implementation("io.insert-koin:koin-androidx-compose:3.2.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.2.0")

    //Firebase
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")
    implementation("com.google.gms:google-services:4.3.15")
    implementation("com.google.firebase:firebase-appdistribution-gradle:4.0.0")
    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation("com.google.firebase:firebase-firestore:24.6.0")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    //temporary data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Excel
    implementation("org.apache.poi:poi:3.10-FINAL")
    implementation("org.apache.poi:poi-ooxml:3.10-FINAL")
    implementation("org.apache.poi:poi-ooxml-schemas:3.10-FINAL")
}
