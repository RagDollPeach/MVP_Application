package com.example.myapplicationmvp.core.mapper

import com.example.myapplicationmvp.core.database.UserEntity
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.UserDto

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
}