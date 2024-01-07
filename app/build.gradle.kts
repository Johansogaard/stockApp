plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("src/debug_bnker.keystore")
            storePassword = "android"
            keyAlias = "debug_bnker"
            keyPassword = "android"
        }
    }
    namespace = "com.example.stockapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stockapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation("junit:junit:4.13.2")
    implementation("androidx.room:room-ktx:2.6.1")
    testImplementation("org.mockito:mockito-core:3.11.2")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("co.yml:ycharts:2.1.0")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation ("com.google.code.gson:gson:2.10")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50") // Hilt compiler
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.21")

    implementation("com.google.dagger:hilt-android-gradle-plugin:2.50")

    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")
    implementation("com.google.accompanist:accompanist-pager:0.33.2-alpha")

    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation ("com.facebook.android:facebook-android-sdk:16.3.0")
    implementation("com.google.gms:google-services:4.4.0")
    implementation("com.google.firebase:firebase-appcheck-playintegrity")
    implementation("com.google.android.gms:play-services-base:18.2.0")

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

//finhnhubb
    implementation ("io.finnhub:kotlin-client:2.0.20")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0") // Check for the latest version
    implementation ("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("com.airbnb.android:lottie-compose:6.3.0")

    //agurk
    androidTestImplementation("io.cucumber:cucumber-android:7.14.0")
    androidTestImplementation("io.cucumber:cucumber-picocontainer:7.14.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("org.mockito:mockito-android:3.11.2")


}