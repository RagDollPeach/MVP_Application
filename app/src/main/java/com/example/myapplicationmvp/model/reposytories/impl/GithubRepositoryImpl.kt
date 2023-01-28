package com.example.myapplicationmvp.model.reposytories.impl

import android.util.Log
import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.core.mapper.UserMapper
import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import com.example.myapplicationmvp.model.database.ReposDAO
import com.example.myapplicationmvp.model.database.RoomRepos
import com.example.myapplicationmvp.model.database.RoomUsers
import com.example.myapplicationmvp.model.database.UserDao
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl (
    private val usersApi: UsersApi,
    private val userDao: UserDao,
    private val repos: ReposDAO,
    private val reposCache: RoomRepos,
    private val usersCache: RoomUsers,
) : GithubRepository {

    private val networkStatus: Single<Boolean> = App.instance.getConnectSingle()

    override fun getUsers(): Single<List<GithubUser>> {
        return networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                Log.d("@@@", "Using api")
                usersCache.fetchFromApi(true, userDao, usersApi)
            } else {
                Log.d("@@@", "Using database")
                usersCache.getFromDb(userDao)
            }
        }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToGitHubUser)
    }

    override fun getAllRepos(user: GithubUser): Single<List<Repo>> {
        return  networkStatus.flatMap { hasConnection ->
            if (hasConnection) {
                Log.d("@@@", "Using api repos")
                reposCache.getRepos(true, repos, usersApi, user)
            } else {
                Log.d("@@@", "Using database repos")
                val userId = user.id.toString()
                reposCache.getReposFromDb(repos, userId)
            }
        }
    }
}