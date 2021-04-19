sealed class DependeciesProvider {

    object App : DependeciesProvider() {
        override fun dependencies(): List<String> {
            return KotlinDependencies.provideLibs() + AndroidDependencies.provideLibs() + AppDependencies.provideLibs()
        }
    }

    object Platform : DependeciesProvider() {
        fun provideFirebase(): String {
            return PlatformDependencies.provideFirebaseBom()
        }

        fun provideFirebaseLibs(): List<String> {
            return PlatformDependencies.provideLibs()
        }
    }

    object UnitTesting : DependeciesProvider() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideUnitTestingLibs()
        }
    }

    object AndroidTesting : DependeciesProvider() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideAndroidTestingLibs()
        }
    }

    object Core : DependeciesProvider() {
        private const val coreModule = ":core"

        override fun dependencies(): List<String> {
            return KotlinDependencies.provideLibs() + AndroidDependencies.provideLibs()
        }

        fun module(): String {
            return coreModule
        }
    }

    open fun dependencies(): List<String> {
        return listOf()
    }
}
