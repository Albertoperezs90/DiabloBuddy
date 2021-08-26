import kotlin.script.experimental.jvm.util.classpathFromClasspathProperty

apply(from = "jacocoFull.gradle.kts")
apply(from = "config/version/version.gradle.kts")

plugins {
    id(GradlePlugin.dependencyUpdate) version GradlePlugin.Versions.dependencyUpdate
    id(GradlePlugin.detekt) version GradlePlugin.Versions.detekt
    jacoco
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("${BuildScriptPlugin.androidGradle}:${BuildScriptPlugin.Versions.androidGradle}")
        classpath("${BuildScriptPlugin.kotlinGradle}:${BuildScriptPlugin.Versions.kotlinGradle}")
        classpath("${BuildScriptPlugin.googleServices}:${BuildScriptPlugin.Versions.googleServices}")
        classpath("${BuildScriptPlugin.crashlytics}:${BuildScriptPlugin.Versions.crashlytics}")
        classpath("${BuildScriptPlugin.appDistribution}:${BuildScriptPlugin.Versions.appDistribution}")
    }
}

jacoco {
    toolVersion = "0.8.7"
}

allprojects {
    apply(plugin = GradlePlugin.detekt)
    apply(from = "${rootProject.rootDir}/jacoco.gradle.kts")

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        detektPlugins("${DetektPlugin.formatting}:${GradlePlugin.Versions.detekt}")
        detektPlugins("${DetektPlugin.cli}:${GradlePlugin.Versions.detekt}")
    }
}

tasks.register("detektAll", io.gitlab.arturbosch.detekt.Detekt::class) {
    val autoFix = project.hasProperty("detektAutoFix")
    val projectSource = file(projectDir)
    val configFile = files("$rootDir/config/detekt/detekt.yml")
    val resourceFiles = "**/resources/**"
    val buildFiles = "**/build/**"

    description = "Detekt task for all modules"
    parallel = true
    ignoreFailures = false
    autoCorrect = autoFix
    buildUponDefaultConfig = true
    setSource(projectSource)
    config.setFrom(configFile)
    exclude(resourceFiles, buildFiles)

    description = "Detekt task for all modules"
    parallel = true
    ignoreFailures = false
    autoCorrect = autoFix
    buildUponDefaultConfig = true
    setSource(projectSource)
    config.setFrom(configFile)
    reports {
        html.enabled = true
        xml.enabled = true
        txt.enabled = false
        sarif.enabled = false
    }
}

tasks.register("detektBaselineAll", io.gitlab.arturbosch.detekt.DetektCreateBaselineTask::class) {
    val projectSource = file(projectDir)
    val configFile = files("$rootDir/config/detekt/detekt.yml")
    val baselineFile = file("$rootDir/config/detekt/baseline.xml")

    description = "Overrides current baseline file"
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(projectSource)
    config.setFrom(configFile)
    baseline.set(baselineFile)

    include("**/*.kt")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
