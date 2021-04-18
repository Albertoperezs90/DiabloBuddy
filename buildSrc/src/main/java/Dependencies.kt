sealed class Dependencies {

    object App : Dependencies() {
        override fun dependencies(): List<String> {
            return KotlinDependencies.provideLibs() + AndroidDependencies.provideLibs() + AppDependencies.provideLibs()
        }
    }

    object Platform : Dependencies() {
        fun provideFirebase(): String {
            return PlatformDependencies.provideFirebaseBom()
        }

        fun provideFirebaseLibs(): List<String> {
            return PlatformDependencies.provideLibs()
        }
    }

    object UnitTesting : Dependencies() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideUnitTestingLibs()
        }
    }

    object AndroidTesting : Dependencies() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideAndroidTestingLibs()
        }
    }

    object Core : Dependencies() {
        override fun dependencies(): List<String> {
            return KotlinDependencies.provideLibs() + AndroidDependencies.provideLibs()
        }

        fun module(): String {
            return ":core"
        }
    }

    open fun dependencies(): List<String> {
        return listOf()
    }
}
