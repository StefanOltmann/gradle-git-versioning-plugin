package de.stefan_oltmann

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneId

@Suppress("unused")
class GradleGitVersioning: Plugin<Project> {

    override fun apply(project: Project) {

        val task = project.tasks.create("setVersionFromGit") { task ->
            task.doLast {
                setVersionFromGit(project.rootProject.projectDir)
            }
        }

        task.group = "versioning"
    }

    private fun setVersionFromGit(projectDir: File) {

        val commitTime = GitUtil.getCommitTime(projectDir) ?: return

        val dateTime = LocalDateTime.ofInstant(
            commitTime,
            ZoneId.systemDefault()
        )

        val versionName = VersionNameGenerator.generateVersionName(dateTime)

        println("Commited on $dateTime. This results in version $versionName")
    }
 }