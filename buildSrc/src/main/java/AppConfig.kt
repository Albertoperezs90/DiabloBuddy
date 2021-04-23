import java.io.File
import java.io.FileOutputStream
import java.util.Properties

object AppConfig {
    const val compileSdk = 30
    const val minSdk = 19
    const val targetSdk = 30
    val versionCode = readVersionCode()
    val versionName = readVersionName()
    const val buildToolsVersion = "30.0.3"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardRules = "proguard-rules.pro"
    const val dimension = "environment"

    private fun readVersionName(): String {
        with(getVersionProperties()) {
            val versionMajor = getProperty("versionMajor")
            val versionMinor = getProperty("versionMinor")
            val versionPatch = getProperty("versionPatch")
            return "$versionMajor.$versionMinor.$versionPatch"
        }
    }

    private fun readVersionCode(): Int {
        with(getVersionProperties()) {
            val versionCode = getProperty("versionCode")
            return versionCode.toInt()
        }
    }

    private fun getVersionProperties(): Properties {
        val versionProperties = Properties()
        val versionFile = File("config/version/version.properties")
        versionProperties.load(versionFile.reader())
        return versionProperties
    }

    private fun updateVersionCode() {
        val properties = getVersionProperties()
        val fos = FileOutputStream(File("config/version/version.properties"))
        properties.setProperty("versionCode", "${versionCode.inc()}")
        properties.store(fos, null)
        fos.close()
    }
}
