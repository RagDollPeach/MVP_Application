package com.example.myapplicationmvp.model.reposytories.impl

import com.example.myapplicationmvp.core.mapper.UserMapper
import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl constructor(
    private val usersApi: UsersApi,
  //  private val userDao: UserDao
): GithubRepository {

//    private fun fetchFromApi(shouldPersist: Boolean): Single<List<GithubUser>> {
//        return usersApi.getAllUsers()
//            .map { it.map(UserMapper::mapToGitHubUser) }
//            .flatMap { userDao.insertAll(it.map(UserMapper)) }
//    }

    override fun getUsers(): Single<List<GithubUser>> {
       return usersApi.getAllUsers()
           .map { it.map(UserMapper::mapToGitHubUser) }
    }

    override fun getUserById(login: String): Single<GithubUser> {
        return usersApi.getUser(login)
            .map(UserMapper::mapToGitHubUser)
    }

    override fun getAllRepos(): Single<List<Repo>> {
        return usersApi.getAllRepos().map { it.toList() }
    }
}