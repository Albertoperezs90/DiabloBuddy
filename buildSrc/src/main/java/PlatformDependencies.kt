object PlatformDependencies : DependencyProvider {

    private const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebase}"

    private const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    private const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    fun provideFirebaseBom(): String {
        return firebaseBom
    }

    override fun provideLibs(): List<String> {
        return listOf(
            firebaseAnalytics,
            firebaseCrashlytics
        )
    }
}