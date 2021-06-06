package de.stefan_oltmann

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneId

@Suppress("unused")
class GradleGitVersioningPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create("gitVersioning", VersioningExtension::class.java, project)
    }
}