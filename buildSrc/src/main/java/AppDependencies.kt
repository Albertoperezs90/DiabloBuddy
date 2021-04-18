object AppDependencies: DependencyProvider {

    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    override fun provideLibs(): List<String> {
        return listOf(retrofit)
    }

}