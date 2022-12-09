package com.example.myapplicationmvp.model.reposytories.impl

import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.reposytories.GithubRepository

class GithubRepositoryImpl: GithubRepository {

    private val repositories = listOf(
        GithubUser("MrFoxy"),
        GithubUser("hater"),
        GithubUser("Wednesday"),
        GithubUser("Friday"),
        GithubUser("SaltajBolTay")
    )

    override fun getUsers(): List<GithubUser> {
        return repositories
    }
}