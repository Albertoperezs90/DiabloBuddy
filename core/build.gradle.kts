plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
}

dependencies {
    api(Dependencies.Core.dependencies())
}