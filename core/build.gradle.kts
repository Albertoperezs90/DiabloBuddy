plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
}

dependencies {
    api(Dependencies.Core.dependencies())
    api(Dependencies.Platform.provideFirebaseLibs())

    implementantionPlatform(Dependencies.Platform.provideFirebase())

    testImplementation(Dependencies.UnitTesting.dependencies())
}