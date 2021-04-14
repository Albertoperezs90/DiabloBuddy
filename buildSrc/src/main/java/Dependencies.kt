import org.gradle.api.artifacts.dsl.DependencyHandler

sealed class Dependencies {

    object App: Dependencies() {
        override fun dependencies(): List<String> {
            return KotlinDependencies.provideLibs() + AndroidDependencies.provideLibs() + AppDependencies.provideLibs()
        }
    }

    object UnitTesting: Dependencies() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideUnitTestingLibs()
        }
    }

    object AndroidTesting: Dependencies() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideAndroidTestingLibs()
        }
    }

    object Core: Dependencies() {

        override fun dependencies(): List<String> {
            return KotlinDependencies.provideLibs() + AndroidDependencies.provideLibs()
        }

        fun module(): String {
            return ":core"
        }
    }

    abstract fun dependencies(): List<String>
}
