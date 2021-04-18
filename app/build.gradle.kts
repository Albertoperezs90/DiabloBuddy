plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")

    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.aperezsi.diablobuddy"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), AppConfig.proguardRules
            )
        }
    }

    flavorDimensions(AppConfig.dimension)

    productFlavors {
        create("production") {
            dimension = AppConfig.dimension
        }

        create("playground") {
            dimension = AppConfig.dimension
            applicationIdSuffix = ".playground"
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementationProject(Dependencies.Core.module())

    androidTestImplementation(Dependencies.AndroidTesting.dependencies())
}

apply(plugin = "com.google.gms.google-services")