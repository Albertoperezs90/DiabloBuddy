object AndroidDependencies: DependencyProvider {

    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val material = "com.google.android.material:material:${Versions.material}"

    override fun provideLibs(): List<String> {
        return listOf(
            coreKtx,
            appcompat,
            constraintLayout,
            material
        )
    }

}