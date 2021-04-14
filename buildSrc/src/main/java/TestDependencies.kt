object TestDependencies {

    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    fun provideUnitTestingLibs(): List<String> {
        return listOf(junit)
    }

    fun provideAndroidTestingLibs(): List<String> {
        return listOf(
            extJUnit,
            espressoCore
        )
    }
}