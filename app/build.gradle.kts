plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")

    id("com.google.firebase.crashlytics")
    id("com.google.firebase.appdistribution")
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
        getByName("debug") {
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isTestCoverageEnabled = false
            debuggable(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), AppConfig.proguardRules
            )
        }
    }

    flavorDimensions(AppConfig.dimension)

    productFlavors {
        create("production") {
            dimension = AppConfig.dimension

            firebaseAppDistribution {
                releaseNotes="QA version to test with official API"
                groups="internal-qa"
            }
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
    implementationProject(DependeciesProvider.Core.module())

    testImplementation(DependeciesProvider.UnitTesting.dependencies())
    androidTestImplementation(DependeciesProvider.AndroidTesting.dependencies())
}

apply(plugin = "com.google.gms.google-services")