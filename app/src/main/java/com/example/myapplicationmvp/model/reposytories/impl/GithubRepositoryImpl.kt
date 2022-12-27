package com.example.myapplicationmvp.model.reposytories.impl

import com.example.myapplicationmvp.core.mapper.UserMapper
import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import com.example.myapplicationmvp.model.database.RoomRepos
import com.example.myapplicationmvp.model.database.RoomUsers
import com.example.myapplicationmvp.model.database.UserDao
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi,
    private val userDao: UserDao,
    private val reposCache: RoomRepos,
    private val usersCache: RoomUsers,
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> {
        return if (true) {
            usersCache.fetchFromApi(true, userDao, usersApi)
        } else {
            usersCache.getFromDb(userDao)
        }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToGitHubUser)
    }

    override fun getAllRepos(): Single<List<Repo>> {
        return reposCache.getRepos(usersApi)
    }
}