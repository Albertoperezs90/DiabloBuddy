plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
}

dependencies {
    api(Dependencies.Core.dependencies())

    implementantionPlatform(Dependencies.Platform.provideFirebase())
    api(Dependencies.Platform.provideFirebaseLibs())
}