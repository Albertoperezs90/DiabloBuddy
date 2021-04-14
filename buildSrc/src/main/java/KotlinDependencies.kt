object KotlinDependencies: DependencyProvider {

    private val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    override fun provideLibs(): List<String> {
        return listOf(
            kotlinStdLib
        )
    }
}