package com.example.myapplicationmvp.model.reposytories

import com.example.myapplicationmvp.model.data.GithubUser
import io.reactivex.rxjava3.core.Single

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getPicture(): Single<String>
}