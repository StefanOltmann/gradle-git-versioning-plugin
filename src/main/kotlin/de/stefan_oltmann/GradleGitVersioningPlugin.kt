package de.stefan_oltmann

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class GradleGitVersioningPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.extensions.create(EXTENSION_NAME, VersioningExtension::class.java, project)

        val printVersionTask = project.tasks.create("printVersion") { task ->
            task.doLast {

                val extension = project.extensions.findByName(EXTENSION_NAME) as VersioningExtension

                println(extension.versionName)
            }
        }

        val printCommitTimeTask = project.tasks.create("printCommitTime") { task ->
            task.doLast {

                val extension = project.extensions.findByName(EXTENSION_NAME) as VersioningExtension

                println(extension.commitTime)
            }
        }

        printVersionTask.group = GROUP_NAME
        printCommitTimeTask.group = GROUP_NAME
    }

    companion object {

        const val EXTENSION_NAME: String = "gitVersioning"
        const val GROUP_NAME: String = "versioning"
    }
}
