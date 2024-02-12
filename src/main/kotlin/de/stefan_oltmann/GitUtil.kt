package de.stefan_oltmann

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File

object GitUtil {

    private fun findRepository(directory: File): Repository? {

        val repositoryBuilder = FileRepositoryBuilder()

        repositoryBuilder.findGitDir(directory)

        repositoryBuilder.findGitDir()

        if (repositoryBuilder.gitDir == null)
            return null

        return repositoryBuilder.build()
    }

    fun getCommitTime(directory: File): Int {

        val repository = findRepository(directory) ?: return 0

        val revision = repository.resolve(Constants.HEAD) ?: return 0

        return repository.parseCommit(revision).commitTime
    }

    fun tagCommit(directory: File, tag: String) {

        val repository = findRepository(directory) ?: return

        val git = Git.wrap(repository)

        git.tag().setName(tag).setForceUpdate(true).call();

        git.push().call()
    }
}
