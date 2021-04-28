object Dependencies {
    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:1.3.72"

    // Android
    const val playCore = "com.google.android.play:core:1.10.0"
    const val coreKtx = "androidx.core:core-ktx:1.3.2"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val material = "com.google.android.material:material:1.3.0"

    // Navigation
    private const val navigationVersion = "2.3.5"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    const val navigationFeatureModule = "androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"

    // Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:26.8.0"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    // Testing

    const val junit = "junit:junit:4.12"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:3.1.0"
    const val robolectric = "org.robolectric:robolectric:4.4"
    const val extJUnit = "androidx.test.ext:junit:1.1.2"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
}
