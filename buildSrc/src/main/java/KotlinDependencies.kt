object KotlinDependencies : DependencyProvider {

    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    override fun provideLibs(): List<String> {
        return listOf(
            kotlinStdLib
        )
    }
}
