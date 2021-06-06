package de.stefan_oltmann

import org.gradle.api.Project
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

open class VersioningExtension(val project: Project) {

    val commitTime by lazy {
        GitUtil.getCommitTime(project.rootProject.projectDir)
    }

    val versionName by lazy {
        generateVersionName()
    }

    private fun generateVersionName(): String {

        if (commitTime == 0)
            return ""

        val dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(commitTime.toLong()),
            ZoneId.systemDefault()
        )

        val versionName = VersionNameGenerator.generateVersionName(dateTime)

        println("Commited on $commitTime = $dateTime. This results in version $versionName")

        return versionName
    }
}
