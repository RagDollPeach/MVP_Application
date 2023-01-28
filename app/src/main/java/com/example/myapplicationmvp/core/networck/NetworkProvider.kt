package com.example.myapplicationmvp.core.networck

import com.example.myapplicationmvp.BuildConfig
import com.example.myapplicationmvp.model.data.GithubUser
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProvider {
    fun usersApi(user: GithubUser?): UsersApi {
        return if (user == null) {
            createRetrofit(BuildConfig.SERVER_URL).create(UsersApi::class.java)
        } else {
            val userRepos = user.reposPath.replace("repos", "").trim()
            createRetrofit(userRepos).create(UsersApi::class.java)
        }
    }

    private fun createRetrofit(url: String): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}