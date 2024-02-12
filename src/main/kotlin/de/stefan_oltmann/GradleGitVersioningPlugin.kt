package de.stefan_oltmann

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

@Suppress("unused")
class GradleGitVersioningPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.extensions.create(EXTENSION_NAME, VersioningExtension::class.java, project)

        val extension = project.extensions.findByName(EXTENSION_NAME) as VersioningExtension

        /* Set the version property. This is the main purpose of this plugin. */
        project.rootProject.version = extension.versionName

        val printVersionTask = project.tasks.create("printVersion") { task ->
            task.doLast {
                println(extension.versionName)
            }
        }

        val printCommitTimeTask = project.tasks.create("printCommitTime") { task ->
            task.doLast {
                println(extension.commitTime)
            }
        }

        val writeVersionFileTask = project.tasks.create("writeVersionFile") { task ->
            task.doLast {

                val versionFile = File(project.rootProject.buildDir, VERSION_FILE_NAME)

                versionFile.writeText(extension.versionName)

                project.logger.lifecycle("Current version '${extension.versionName}' was written to ${versionFile.absolutePath}")
            }
        }

        val writeVersionGitTagTask = project.tasks.create("writeVersionGitTag") { task ->
            task.doLast {

                GitUtil.tagCommit(project.rootDir, extension.versionName)

                project.logger.lifecycle("Current version '${extension.versionName}' was written to Git tag")
            }
        }

        printVersionTask.group = GROUP_NAME
        printCommitTimeTask.group = GROUP_NAME
        writeVersionFileTask.group = GROUP_NAME
        writeVersionGitTagTask.group = GROUP_NAME

        /*
         * Automatically write a build_version.txt
         */
        val buildTask = project.rootProject.tasks.findByName("build")
        buildTask?.finalizedBy(writeVersionFileTask)
    }

    companion object {

        const val EXTENSION_NAME: String = "gitVersioning"
        const val GROUP_NAME: String = "versioning"
        const val VERSION_FILE_NAME: String = "build_version.txt"
    }
}
