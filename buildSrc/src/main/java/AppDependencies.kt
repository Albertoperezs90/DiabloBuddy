object AppDependencies: DependencyProvider {

    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    override fun provideLibs(): List<String> {
        return listOf(retrofit)
    }

}