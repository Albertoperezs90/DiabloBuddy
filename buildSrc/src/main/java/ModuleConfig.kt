import kotlin.reflect.full.memberProperties

private const val FEATURE_PREFIX = ":feature_"

object ModuleConfig {
    const val app = ":app"
    const val core = ":core"
    const val core_testing = ":core-testing"
    const val feature_menu = ":feature_menu"
    const val feature_skills = ":feature_skills"

    private fun getAllModules() = ModuleConfig::class.memberProperties
            .filter { it.isConst }
            .map { it.getter.call().toString() }
            .toSet()

    fun getDynamicFeatureModules() = getAllModules()
            .filter { it.startsWith(FEATURE_PREFIX) }
            .toMutableSet()
}
