apply(plugin = "jacoco")

project.afterEvaluate {
    val kotlinClassesFolder = getKotlinClassesFolder() ?: return@afterEvaluate
    val jacocoExec = getJacocoExec() ?: return@afterEvaluate
    tasks.register("${project.name}JacocoTestReport", JacocoReport::class) {
        group = "coverage report"
        dependsOn("test")

        if (isAndroidModule(project)) {
            // dependsOn("createPlaygroundDebugCoverageReport")
        }

        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
        val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*")
        val debugTree = fileTree(kotlinClassesFolder) { setExcludes(fileFilter) }
        val mainSrc = "${project.projectDir}/src/main/java"

        sourceDirectories.setFrom(files(mainSrc))
        classDirectories.setFrom(files(debugTree))
        executionData.setFrom(fileTree("$buildDir") {
            setIncludes(listOf(jacocoExec, "outputs/code-coverage/connected/*coverage.ec"))
        })
    }
}

fun getKotlinClassesFolder(): String? {
    var kotlinFolder: String? = null
    fileTree("$buildDir/tmp/kotlin-classes").visit {
        if (this.file.isDirectory && this.file.name.endsWith("debug", ignoreCase = true)) {
            kotlinFolder = this.file.path
            stopVisiting()
        }
    }
    return kotlinFolder
}

fun getJacocoExec(): String? {
    var jacocoExec: String? = null
    fileTree("$buildDir/jacoco").visit {
        if (this.file.name.endsWith("DebugUnitTest.exec")) {
            jacocoExec = this.file.path.substringAfter("$buildDir")
            stopVisiting()
        }
    }
    return jacocoExec
}

fun isAndroidModule(project: Project): Boolean {
    val isAndroidLibrary = project.plugins.hasPlugin("com.android.library")
    val isAndroidApp = project.plugins.hasPlugin("com.android.application")
    return isAndroidLibrary || isAndroidApp
}
