package com.example.myapplicationmvp.core.networck

import com.example.myapplicationmvp.model.data.Repo
import com.example.myapplicationmvp.model.data.UserDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("/users")
    fun getAllUsers(): Single<List<UserDto>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login: String): Single<UserDto>

    @GET("repos")
    fun getAllRepos(): Single<List<Repo>>
}