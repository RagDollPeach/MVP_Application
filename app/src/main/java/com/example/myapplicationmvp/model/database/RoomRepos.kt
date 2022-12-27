package com.example.myapplicationmvp.model.database

import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.Repo
import io.reactivex.rxjava3.core.Single

interface RoomRepos {

    fun getRepos(usersApi: UsersApi): Single<List<Repo>>
}