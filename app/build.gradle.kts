plugins {
    id(GradlePlugin.androidApplication)
    id(GradlePlugin.kotlinAndroid)
    id(GradlePlugin.kotlinAndroidExtensions)
    id(GradlePlugin.crashlytics)
    id(GradlePlugin.appDistribution)
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AppConfig.applicationId
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
        create(AppConfig.Dimension.production) {
            dimension = AppConfig.dimension

            firebaseAppDistribution {
                releaseNotes = "QA version to test with official API"
                groups = "internal-qa"
            }
        }

        create(AppConfig.Dimension.playground) {
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures.viewBinding = true

    dynamicFeatures = ModuleConfig.getDynamicFeatureModules()
}

dependencies {
    implementation(Dependencies.playCore)

    api(Dependencies.kotlinStdLib)
    api(Dependencies.coreKtx)
    api(Dependencies.navigationFragment)
    api(Dependencies.navigationUi)
    api(Dependencies.navigationFeatureModule)
    api(Dependencies.viewModel)
    api(Dependencies.constraintLayout)
    api(Dependencies.material)
    api(platform(Dependencies.firebaseBom))
    api(Dependencies.firebaseAnalytics)
    api(Dependencies.firebaseCrashlytics)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.robolectric)
    androidTestImplementation(Dependencies.extJUnit)
    androidTestImplementation(Dependencies.espressoCore)
}

apply(plugin = "com.google.gms.google-services")
