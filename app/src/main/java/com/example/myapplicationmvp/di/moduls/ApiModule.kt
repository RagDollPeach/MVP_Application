package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.core.networck.NetworkProvider
import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.data.GithubUser
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    @Provides
    fun provideNetworkProvider(): NetworkProvider {
        return NetworkProvider()
    }

    @Provides
    fun provideApi(networkProvider: NetworkProvider, githubUser: GithubUser?): UsersApi {
        return networkProvider.usersApi(githubUser)
    }
}