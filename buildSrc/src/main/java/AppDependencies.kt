import org.gradle.api.artifacts.dsl.DependencyHandler

sealed class AppDependencies {

    object Kotlin: AppDependencies() {
        override fun dependencies(): List<String> {
            return KotlinDependencies.provideLibs()
        }
    }

    object Android: AppDependencies() {
        override fun dependencies(): List<String> {
            return AndroidDependencies.provideLibs()
        }

    }

    object UnitTesting: AppDependencies() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideUnitTestingLibs()
        }
    }

    object AndroidTesting: AppDependencies() {
        override fun dependencies(): List<String> {
            return TestDependencies.provideAndroidTestingLibs()
        }
    }

    abstract fun dependencies(): List<String>
}
