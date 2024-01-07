

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21") // Kotlin Gradle Plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50") // Hilt Gradle Plugin
        classpath("com.google.gms:google-services:4.4.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.21")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}