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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(AppDependencies.Kotlin.dependencies() + AppDependencies.Android.dependencies())
    testImplementation(AppDependencies.UnitTesting.dependencies())
    androidTestImplementation(AppDependencies.AndroidTesting.dependencies())
}