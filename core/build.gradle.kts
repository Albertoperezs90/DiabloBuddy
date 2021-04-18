plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
}

dependencies {
    apiPlatform(Dependencies.Platform.provideFirebase())

    api(Dependencies.Core.dependencies())
    api(Dependencies.Platform.provideFirebaseLibs())


    testImplementation(Dependencies.UnitTesting.dependencies())
}