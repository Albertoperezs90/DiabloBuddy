plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementantionPlatform(Dependencies.Platform.provideFirebase())

    implementationProject(Dependencies.Core.module())

    implementation(Dependencies.Platform.provideFirebaseLibs())

    testImplementation(Dependencies.UnitTesting.dependencies())
    androidTestImplementation(Dependencies.AndroidTesting.dependencies())
}

apply(plugin = "com.google.gms.google-services")