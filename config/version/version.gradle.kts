@file:Suppress("HasPlatformType")

tasks.register("updateVersionCode", Task::class) {
    group = "versioning"
    doLast {
        val versionProperties = java.util.Properties()
        val versionFile = File("config/version/version.properties")
        versionProperties.load(versionFile.reader())
        val fos = java.io.FileOutputStream(File("config/version/version.properties"))
        val versionCode = versionProperties.getProperty("versionCode").toInt()
        versionProperties.setProperty("versionCode", "${versionCode.inc()}")
        versionProperties.store(fos, null)
        fos.close()
    }
}
val commitVersionProperties by tasks.creating {
    group = "versioning"
    val versionCode = AppConfig.versionCode
    doLast {
        exec {
            executable("git")
            args("add", "-A")
        }
        exec {
            executable("git")
            args("commit", "-a", "-m", "Upgrade version to $versionCode and deploy QA [ci-skip]")
        }
        exec {
            executable("git")
            args("tag", "-f", "$versionCode")
        }
    }
}