plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
}

dependencies {
    apiPlatform(DependeciesProvider.Platform.provideFirebase())

    api(DependeciesProvider.Core.dependencies())
    api(DependeciesProvider.Platform.provideFirebaseLibs())


    testImplementation(DependeciesProvider.UnitTesting.dependencies())
}