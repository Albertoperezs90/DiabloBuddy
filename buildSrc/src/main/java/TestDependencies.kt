object TestDependencies {

    private const val junit = "junit:junit:${Versions.junit}"
    private const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    private const val roboelectric = "org.robolectric:robolectric:${Versions.roboelectric}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    fun provideUnitTestingLibs(): List<String> {
        return listOf(
            junit,
            mockitoKotlin,
            roboelectric
        )
    }

    fun provideAndroidTestingLibs(): List<String> {
        return listOf(
            extJUnit,
            espressoCore
        )
    }
}