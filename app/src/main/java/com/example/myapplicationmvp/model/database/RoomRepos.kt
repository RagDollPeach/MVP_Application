package com.example.myapplicationmvp.model.database

import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import io.reactivex.rxjava3.core.Single

interface RoomRepos {

    fun getRepos(shouldPersist: Boolean, repos: ReposDAO,usersApi: UsersApi,user: GithubUser): Single<List<Repo>>

    fun getReposFromDb(repos: ReposDAO,userId: String): Single<List<Repo>>
}