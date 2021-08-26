object Dependencies {
    // Kotlin
    private const val kotlinVersion = "1.5.21"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}"

    // Injection
    private const val daggerVersion = "2.35"
    const val dagger = "com.google.dagger:dagger:${daggerVersion}"
    const val daggerKapt = "com.google.dagger:dagger-compiler:${daggerVersion}"

    // Android
    private const val lifecyleVersion = "2.3.1"
    const val playCore = "com.google.android.play:core:1.10.0"
    const val coreKtx = "androidx.core:core-ktx:1.3.2"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecyleVersion"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecyleVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val material = "com.google.android.material:material:1.3.0"

    // Parser
    private const val moshiVersion = "1.12.0"
    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    // Navigation
    private const val navigationVersion = "2.3.5"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    const val navigationFeatureModule = "androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion"

    // Networking
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    // Firebase
    const val firebaseBom = "com.google.firebase:firebase-bom:26.8.0"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    // Testing
    const val junit = "junit:junit:4.12"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:3.2.0"
    const val robolectric = "org.robolectric:robolectric:4.4"
    const val extJUnit = "androidx.test.ext:junit:1.1.2"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
}
