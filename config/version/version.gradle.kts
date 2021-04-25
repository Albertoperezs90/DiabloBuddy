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

task("commitVersionProperties") {
    group = "versioning"
    doLast {
        exec {
            commandLine("cmd", "git", "add", ".")
            commandLine("cmd", "git", "commit", "-m", "'Update versionCode [skip ci]'")
            commandLine("cmd", "git", "push", "--force", "origin")
        }
    }
}