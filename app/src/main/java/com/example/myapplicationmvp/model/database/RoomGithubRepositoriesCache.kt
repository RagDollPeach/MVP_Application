package com.example.myapplicationmvp.model.database


import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.Repo
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache : RoomRepos {

    override fun getRepos(usersApi: UsersApi): Single<List<Repo>> {
        return usersApi.getAllRepos().map { it.toList() }
    }
}