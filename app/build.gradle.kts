plugins {
    id(GradlePlugin.androidApplication)
    id(GradlePlugin.kotlinAndroid)
    id(GradlePlugin.kotlinKapt)
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
        multiDexEnabled = true
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        buildConfigField("String", "BASE_BATTLENET_URL", "\".battle.net\"")
        buildConfigField("String", "BASE_API_URL", "\".api.blizzard.com\"")
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
            buildConfigField("String", "CLIENT_USERNAME", "\"${System.getenv("CLIENT_USERNAME")}\"")
            buildConfigField("String", "CLIENT_PASSWORD", "\"${System.getenv("CLIENT_PASSWORD")}\"")

            firebaseAppDistribution {
                releaseNotes = "QA version to test with official API"
                groups = "internal-qa"
            }
        }

        create(AppConfig.Dimension.playground) {
            dimension = AppConfig.dimension
            applicationIdSuffix = ".playground"
            buildConfigField("String", "CLIENT_USERNAME", "\"e37c42d4634f494f86b04bf237b16bc0\"")
            buildConfigField("String", "CLIENT_PASSWORD", "\"fKCcwtLotZvxw8zVOVuGH8Inl3hsPSGc\"")

            firebaseAppDistribution {
                releaseNotes = "QA version to test with official API"
                groups = "internal-qa"
            }
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

    lintOptions {
        isAbortOnError = false
    }

    buildFeatures.viewBinding = true

    dynamicFeatures = ModuleConfig.getDynamicFeatureModules()
}

dependencies {
    implementation(project(ModuleConfig.core))
    implementation(project(ModuleConfig.core_testing))
    implementation(Dependencies.playCore)

    api(Dependencies.retrofit)
    api(Dependencies.moshi)
    api(Dependencies.moshiConverter)

    kapt(Dependencies.moshiCodegen)
    kapt(Dependencies.daggerKapt)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.robolectric)
    androidTestImplementation(Dependencies.extJUnit)
    androidTestImplementation(Dependencies.espressoCore)
}

apply(plugin = "com.google.gms.google-services")
