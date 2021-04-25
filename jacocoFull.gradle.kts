apply(plugin = "jacoco")

tasks.register("jacocoFullReport",JacocoReport::class) {
    group = "Coverage report"
    val projectsSources = mutableListOf<ConfigurableFileCollection>()
    val projectsClassDirectories = mutableListOf<ConfigurableFileCollection>()
    val projectsExecutionDatas = mutableListOf<ConfigurableFileCollection>()
    subprojects.forEach { project ->
        val taskName = "${project.name}JacocoTestReport"
        val task = project.tasks.findByName(taskName) as? JacocoReport ?: return@forEach
        dependsOn(task)
        projectsSources.add(task.sourceDirectories)
        projectsClassDirectories.add(task.classDirectories)
        projectsExecutionDatas.add(task.executionData)
    }
    val sourceFiles = files(projectsSources)
    additionalSourceDirs.setFrom(sourceFiles)
    sourceDirectories.setFrom(sourceFiles)

    classDirectories.setFrom(files(projectsClassDirectories))
    executionData.setFrom(files(projectsExecutionDatas))

    reports {
        html.isEnabled = true
        html.destination = file("build/reports/jacoco/html")

        xml.isEnabled = true
        xml.destination = file("build/reports/jacoco/jacocoFullReport.xml")
    }

    doFirst {
        executionData.setFrom(files(executionData.filter { it.exists() }))
    }
}