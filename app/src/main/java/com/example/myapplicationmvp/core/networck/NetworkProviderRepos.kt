package com.example.myapplicationmvp.core.networck

import com.example.myapplicationmvp.model.data.GithubUser
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProviderRepos(user: GithubUser) {

    private val userRepos = user.reposPath.replace("repos", "").trim()
    val usersApi: UsersApi by lazy { createRetrofit(userRepos).create(UsersApi::class.java) }

    private fun createRetrofit(url: String): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}