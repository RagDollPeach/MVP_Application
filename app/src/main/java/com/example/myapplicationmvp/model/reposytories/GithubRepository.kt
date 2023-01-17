package com.example.myapplicationmvp.model.reposytories

import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import io.reactivex.rxjava3.core.Single

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>

    fun getUserById(login: String): Single<GithubUser>

    fun getAllRepos(user: GithubUser): Single<List<Repo>>
}