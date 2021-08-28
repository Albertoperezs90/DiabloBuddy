plugins {
    id(GradlePlugin.androidLibrary)
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
    implementation(project(ModuleConfig.core))
    api(Dependencies.junit)
    api(Dependencies.mockitoKotlin)
    api(Dependencies.robolectric)

    kapt(Dependencies.daggerKapt)
}
