package de.stefan_oltmann;

import org.eclipse.jgit.api.Git
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test;

class GradleGitVersioningPluginTest {

    @Test
    fun test() {

        /* Create a new Gradle project */
        val project: Project = ProjectBuilder.builder().build()

        /* Create a new Git repo and commit something. */
        val git = Git.init().setDirectory(project.rootDir).call()
        val commit = git.commit().setMessage("initial commit").setAllowEmpty(true).call()

        /* Apply the plugin */
        project.pluginManager.apply("de.stefan_oltmann.git-versioning")
        val extension: VersioningExtension =
            project.extensions.findByName("gitVersioning") as VersioningExtension

        /* Check values */
        assertNotNull(extension)
        assertEquals(commit.commitTime, extension.commitTime)
        assertEquals(
            VersionNameGenerator.generateVersionName(commit.commitTime),
            extension.versionName
        )
    }
}
