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
    val stdout = java.io.ByteArrayOutputStream()
    doLast {
        exec {
            commandLine = mutableListOf("git", "add", ".")
            commandLine = mutableListOf("git", "commit", "-m", "'Update versionCode [skip ci]'")
            commandLine = mutableListOf("git", "push", "--no-verify")
            standardOutput = stdout
            println(stdout.toString().trim())
        }
    }
}