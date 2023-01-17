package com.example.myapplicationmvp.di

import com.example.myapplicationmvp.model.reposytories.GithubRepository
import dagger.Component


interface AppComponent {

    fun getUsersRepos(): GithubRepository
}