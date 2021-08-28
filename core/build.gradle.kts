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
    api(Dependencies.kotlinStdLib)
    api(Dependencies.kotlinReflect)
    api(Dependencies.coreKtx)
    api(Dependencies.dagger)
    api(Dependencies.navigationFeatureModule)
    api(Dependencies.navigationFragment)
    api(Dependencies.navigationUi)
    api(Dependencies.viewModel)
    api(Dependencies.lifecycleLivedata)
    api(Dependencies.constraintLayout)
    api(Dependencies.material)
    api(platform(Dependencies.firebaseBom))
    api(Dependencies.firebaseAnalytics)
    api(Dependencies.firebaseCrashlytics)

    kapt(Dependencies.daggerKapt)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.robolectric)
}
