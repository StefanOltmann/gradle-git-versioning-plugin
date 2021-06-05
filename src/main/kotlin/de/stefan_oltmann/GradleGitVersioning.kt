package de.stefan_oltmann

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.util.*

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

        val commitTimeMillis = GitUtil.getCommitMillis(projectDir)

        if (commitTimeMillis == 0L)
            return

        println("Commited on $commitTimeMillis")

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = commitTimeMillis

        println("Commit on ${calendar.time}")
    }
}