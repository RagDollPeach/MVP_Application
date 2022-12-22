package com.example.myapplicationmvp.core.mapper

import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.UserDto

object UserMapper {

    fun mapToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl,
            repos = dto.repos
        )
    }
}