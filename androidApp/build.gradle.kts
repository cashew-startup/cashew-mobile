plugins {
    id("com.android.application")
    kotlin("android")
}

val isLocalBuild = System.getenv("IS_CI") != "true"

android {
    namespace = "com.cashew.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.cashew.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/*"
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = !isLocalBuild
            isShrinkResources = !isLocalBuild
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.decompose.core)
    implementation(libs.decompose.compose)
    implementation(project(":core"))
    implementation(project(":features"))

    implementation(libs.bundles.compose)
    implementation(libs.moko.resources.core)
    implementation(libs.moko.resources.compose)
}