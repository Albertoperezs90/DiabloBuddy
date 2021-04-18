object AndroidDependencies : DependencyProvider {

    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val material = "com.google.android.material:material:${Versions.material}"

    override fun provideLibs(): List<String> {
        return listOf(
                coreKtx,
                viewModel,
                constraintLayout,
                material
        )
    }

}