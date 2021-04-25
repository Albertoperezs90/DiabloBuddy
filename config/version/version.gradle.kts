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
    doLast {
        val versionCode = AppConfig.versionCode
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
            args("push", "--no-verify")
        }
    }
}

val commitTagQA by tasks.creating {
    group = "versioning"
    doLast {
        val versionCode = AppConfig.versionCode
        exec {
            executable("git")
            args("tag", "-f", "QA-$versionCode")
        }
        exec {
            executable("git")
            args("push", "origin", "--no-verify", "QA-$versionCode")
        }
    }
}

val commitTagProduction by tasks.creating {
    group = "versioning"
    doLast {
        val versionName = AppConfig.versionName
        exec {
            executable("git")
            args("tag", "-f", "Production-$versionName")
        }
        exec {
            executable("git")
            args("push", "origin", "--no-verify", "Production-$versionName")
        }
    }
}