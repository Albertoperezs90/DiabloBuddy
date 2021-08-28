apply(plugin = GradlePlugin.jacoco)

project.afterEvaluate {
    val kotlinClassesFolder = getKotlinClassesFolder() ?: return@afterEvaluate
    val jacocoExec = getJacocoExec() ?: return@afterEvaluate

    tasks.register("${project.name}JacocoTestReport", JacocoReport::class) {
        group = "coverage report"
        dependsOn("test")

        jacocoClasspath = rootProject.configurations["jacocoAnt"]

        if (isAndroidModule(project)) {
            // dependsOn("createPlaygroundDebugCoverageReport")
        }

        reports {
            xml.destination = file("${project.buildDir}/reports/jacoco/${project.name}JacocoTestReport/${project.name}JacocoTestReport.xml")
            xml.isEnabled = true
            html.destination = file("${project.buildDir}/reports/jacoco/${project.name}JacocoTestReport/html/")
            html.isEnabled = true
        }
        val filterBuildFiles = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*")
        val filterDi = listOf("**/di/**")
        val filterBaseFiles = listOf("**/base/**")
        val filterModels = listOf("**/**Response**")
        val filterViews = listOf("**/views/**")
        val filterNetwork = listOf("**/**Interceptor**")
        val fileFilter = filterBuildFiles + filterDi + filterBaseFiles + filterModels + filterViews + filterNetwork
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
