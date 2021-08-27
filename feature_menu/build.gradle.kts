plugins {
    id(GradlePlugin.androidDynamicFeature)
    id(GradlePlugin.kotlinAndroid)
    id(GradlePlugin.kotlinKapt)
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

    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        isAbortOnError = false
    }
}

dependencies {
    implementation(project(ModuleConfig.app))
    implementation(project(ModuleConfig.core))
    implementation(project(ModuleConfig.core_testing))

    kapt(Dependencies.moshiCodegen)
    kapt(Dependencies.daggerKapt)
}
