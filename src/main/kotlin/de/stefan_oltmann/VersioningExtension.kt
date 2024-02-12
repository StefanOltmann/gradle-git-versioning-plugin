package de.stefan_oltmann

import org.gradle.api.Project
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

open class VersioningExtension(val project: Project) {

    val commitTime by lazy {
        GitUtil.getCommitTime(project.rootDir)
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

        if (project.logger.isLifecycleEnabled && !project.logger.isQuietEnabled)
            project.logger.lifecycle("This is version $versionName (commited on $dateTime).")

        return versionName
    }
}
