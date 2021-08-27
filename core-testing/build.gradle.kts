apply(plugin = "shot")

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

        testApplicationId = "com.aperezsi.diablobuddy.test"
        testInstrumentationRunner = "com.karumi.shot.ShotTestRunner"
    }

    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {
    implementation(project(ModuleConfig.core))
    api(Dependencies.junit)
    api(Dependencies.mockitoKotlin)
    api(Dependencies.robolectric)
    api("androidx.test.espresso:espresso-core:3.4.0")
    api("androidx.test.espresso:espresso-intents:3.4.0")

    kapt(Dependencies.daggerKapt)
}
