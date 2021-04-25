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

tasks.register("commitVersionProperties", Exec::class) {
    group = "versioning"
    doLast {
        description = "Commit and push version properties file"
        commandLine("git", "add", ".")
        commandLine("git", "commit", "-m", "Update versionCode [skip ci]")
        commandLine("git", "push", "origin")
    }
}