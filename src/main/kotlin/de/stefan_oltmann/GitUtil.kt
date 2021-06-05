package de.stefan_oltmann

import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File

object GitUtil {

    fun getCommitMillis(directory: File): Long {

        val repositoryBuilder = FileRepositoryBuilder()

        repositoryBuilder.findGitDir(directory)

        repositoryBuilder.findGitDir()

        if (repositoryBuilder.gitDir == null) {
            println("No .git dir found.")
            return 0
        }

        println("Using .git dir ${repositoryBuilder.gitDir.absolutePath}")

        val repository = repositoryBuilder.build()

        val revision = repository.resolve(Constants.HEAD)

        if (revision == null) {
            println("No head revision found.")
            return 0
        }

        val commitTime = repository.parseCommit(revision).commitTime

        return commitTime.toLong() * 1000
    }
}