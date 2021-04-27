plugins {
    id(GradlePlugin.androidDynamicFeature)
    id(GradlePlugin.kotlinAndroid)
}

android {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)

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
        create(AppConfig.Dimension.production) {
            dimension = AppConfig.dimension
        }

        create(AppConfig.Dimension.playground) {
            dimension = AppConfig.dimension
            applicationIdSuffix = ".playground"
        }
    }
}

dependencies {
    implementation(project(":app"))
}