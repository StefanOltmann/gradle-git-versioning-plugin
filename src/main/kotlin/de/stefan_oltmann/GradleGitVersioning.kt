package de.stefan_oltmann

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneId

@Suppress("unused")
class GradleGitVersioning: Plugin<Project> {

    private lateinit var project: Project

    override fun apply(project: Project) {

        this.project = project

        val task = project.tasks.create("setVersionFromGit") { task ->
            task.doLast {

                val versionName = generateVersioName(project.rootProject.projectDir)

                versionName?.let { project.version = it }
            }
        }

        task.group = "versioning"
    }

    private fun generateVersioName(projectDir: File): String? {

        val commitTime = GitUtil.getCommitTime(projectDir) ?: return null

        val dateTime = LocalDateTime.ofInstant(
            commitTime,
            ZoneId.systemDefault()
        )

        val versionName = VersionNameGenerator.generateVersionName(dateTime)

        println("Commited on $dateTime. This results in version $versionName")

        return versionName
    }
 }