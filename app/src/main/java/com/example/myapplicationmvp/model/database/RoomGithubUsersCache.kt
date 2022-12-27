package com.example.myapplicationmvp.model.database

import com.example.myapplicationmvp.core.mapper.UserMapper
import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.core.utils.doCompletableIf
import com.example.myapplicationmvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache : RoomUsers {

    override fun fetchFromApi(shouldPersist: Boolean, userDao: UserDao, usersApi: UsersApi): Single<List<GithubUser>> {
        return usersApi.getAllUsers()
            .doCompletableIf(shouldPersist) {
                userDao.insertAll(it.map(UserMapper::mapToEntity))
            }.map { it.map(UserMapper::mapToGitHubUser) }
    }

    override fun getFromDb(userDao: UserDao): Single<List<GithubUser>> {
        return userDao.queryForAllUsers().map { it.map(UserMapper::mapToGitHubUser) }
    }
}