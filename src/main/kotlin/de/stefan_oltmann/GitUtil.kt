package de.stefan_oltmann

import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File
import java.time.Instant

object GitUtil {

    fun getCommitTime(directory: File): Instant? {

        val repositoryBuilder = FileRepositoryBuilder()

        repositoryBuilder.findGitDir(directory)

        repositoryBuilder.findGitDir()

        if (repositoryBuilder.gitDir == null) {
            println("No .git dir found.")
            return null
        }

        println("Using .git dir ${repositoryBuilder.gitDir.absolutePath}")

        val repository = repositoryBuilder.build()

        val revision = repository.resolve(Constants.HEAD)

        if (revision == null) {
            println("No head revision found.")
            return null
        }

        val commitTime = repository.parseCommit(revision).commitTime

        return Instant.ofEpochSecond(commitTime.toLong())
    }
}