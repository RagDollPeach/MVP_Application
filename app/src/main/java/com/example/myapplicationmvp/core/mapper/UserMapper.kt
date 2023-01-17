package com.example.myapplicationmvp.core.mapper

import com.example.myapplicationmvp.model.database.UserEntity
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import com.example.myapplicationmvp.model.data.UserDto
import com.example.myapplicationmvp.model.database.RepoObjectEntity

object UserMapper {

    fun mapToGitHubUser(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposPath = dto.reposPath,
        )
    }

    fun mapToGitHubUser(userEntity: UserEntity): GithubUser {
        return GithubUser(
            id = userEntity.id,
            login = userEntity.login,
            avatarUrl = userEntity.avatarUrl,
            reposPath = userEntity.reposPath,
        )
    }

    fun mapToEntity(dto: UserDto): UserEntity {
        return UserEntity(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            reposPath = dto.reposPath
        )
    }

    fun mapToRepoEntity(repo: Repo, user: GithubUser): RepoObjectEntity {
        return RepoObjectEntity(
            id = repo.id.toLong(),
            name = repo.name,
            forks = repo.forksCount.toInt(),
            userId = user.id
        )
    }

    fun mapToRepo(repo: RepoObjectEntity): Repo {
        return Repo(
            id = repo.id.toString(),
            name = repo.name,
            forksCount = repo.forks.toString(),
        )
    }
}