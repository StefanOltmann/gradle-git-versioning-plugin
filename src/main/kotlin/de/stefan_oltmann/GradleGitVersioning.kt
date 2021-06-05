package de.stefan_oltmann

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class GradleGitVersioning: Plugin<Project> {

    override fun apply(project: Project) {

        val task = project.tasks.create("setVersionFromGit") { task ->
            task.doLast {
                println("Hello from GradleGitVersioning")
            }
        }

        task.group = "versioning"
    }
}