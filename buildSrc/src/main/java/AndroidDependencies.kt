object AndroidDependencies: DependencyProvider {

    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val material = "com.google.android.material:material:${Versions.material}"

    override fun provideLibs(): List<String> {
        return listOf(
            coreKtx,
            viewModel,
            constraintLayout,
            material
        )
    }

}