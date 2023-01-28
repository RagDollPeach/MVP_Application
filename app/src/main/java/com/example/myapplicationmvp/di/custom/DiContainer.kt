package com.example.myapplicationmvp.di.custom

import com.example.myapplicationmvp.di.moduls.GitHubModule
import com.example.myapplicationmvp.di.moduls.NavigationModule
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.presenter.MainPresenter
import com.example.myapplicationmvp.presenter.UserDataPresenter
import com.example.myapplicationmvp.presenter.UserPresenter
import dagger.BindsInstance
import dagger.Component

@Component(modules = [GitHubModule::class, NavigationModule::class])
interface DiContainer {

    fun inject(presenter: MainPresenter)

    fun inject(presenter: UserPresenter)

    fun inject(presenter: UserDataPresenter)

    @Component.Builder
    interface Builder {

        fun build(): DiContainer

        @BindsInstance
        fun user(user: GithubUser?): Builder
    }
}

