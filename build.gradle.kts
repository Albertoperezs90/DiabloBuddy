import io.gitlab.arturbosch.detekt.Detekt

apply(from = "jacocoFull.gradle.kts")

plugins {
    id("name.remal.check-dependency-updates").version(Versions.dependencyUpdates)
    id("io.gitlab.arturbosch.detekt").version(Versions.detekt)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.google.gms:google-services:${Versions.googleServices}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsGradle}")
    }

}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(from = "${rootProject.rootDir}/jacoco.gradle.kts")

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}")
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}")
    }
}

tasks.register<Detekt>("detektAll") {
    val autoFix = project.hasProperty("detektAutoFix")
    val projectSource = file(projectDir)
    val configFile = files("$rootDir/config/detekt/detekt.yml")
    val kotlinFiles = "**/*.kt"
    val resourceFiles = "**/resources/**"
    val buildSrc = ""
    val buildFiles = "**/build/**"

    description = "Detekt task for all modules"
    parallel = true
    ignoreFailures = false
    autoCorrect = autoFix
    buildUponDefaultConfig = true
    setSource(projectSource)
    config.setFrom(configFile)
    include(kotlinFiles)
    exclude(buildSrc, resourceFiles, buildFiles)
    reports {
        html.enabled = true
        xml.enabled = true
        txt.enabled = false
        sarif.enabled = false
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}