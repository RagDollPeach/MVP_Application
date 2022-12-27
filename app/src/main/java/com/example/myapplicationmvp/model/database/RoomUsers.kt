package com.example.myapplicationmvp.model.database

import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single

interface RoomUsers {

    fun fetchFromApi(shouldPersist: Boolean, userDao: UserDao, usersApi: UsersApi): Single<List<GithubUser>>

    fun getFromDb(userDao: UserDao): Single<List<GithubUser>>
}