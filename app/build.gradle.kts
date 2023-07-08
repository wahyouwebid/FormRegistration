import buildtype.AndroidBuildType
import config.AndroidConfig
import dependencies.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        namespace = AndroidConfig.APP_NAME_SPACE
        minSdk = AndroidConfig.MIN_SDK
        versionCode = AndroidConfig.VERSION_CODE
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENT_RUNNER
    }

    buildTypes {
        named(AndroidBuildType.DEBUG) {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"https://api.goapi.id/v1/\"")
            buildConfigField("String", "apiKey", "\"Mz4GqJZQbtqFh7o3UVTaEvUQ3qpX7q\"")
        }

        named(AndroidBuildType.RELEASE) {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "baseUrl", "\"https://api.goapi.id/v1/\"")
            buildConfigField("String", "apiKey", "\"Mz4GqJZQbtqFh7o3UVTaEvUQ3qpX7q\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    //UI
    implementation(Dependencies.ANDROIDX_CORE)
    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(Dependencies.ANDROIDX_ACTIVITY_KTX)
    implementation(Dependencies.ANDROIDX_FRAGENT_KTX)

    // Navigation Component
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)

    //Networking
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_RX_JAVA)
    implementation(Dependencies.CONVERTER_GSON)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_INTERCEPTOR)
    debugImplementation(Dependencies.CHUCKER)
    releaseImplementation(Dependencies.CHUCKER_NOOP)

    //Dependency Injection
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_ANDROIDX_COMPILER)

    // RxJava
    implementation(Dependencies.RX_JAVA)
    implementation(Dependencies.RX_ANDROID)
    implementation(Dependencies.RX_BINDING)

    //Testing
    testImplementation(Dependencies.JUNIT)
    testImplementation(Dependencies.MOCKITO)
    androidTestImplementation(Dependencies.ANDROIDX_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO)

}
