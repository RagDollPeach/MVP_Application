package com.example.myapplicationmvp.model.reposytories

import com.example.myapplicationmvp.model.data.GithubUser

interface GithubRepository {
    fun getUsers(): List<GithubUser>
}