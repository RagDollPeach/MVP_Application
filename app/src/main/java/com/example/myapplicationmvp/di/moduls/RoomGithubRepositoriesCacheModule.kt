package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.model.database.RoomGithubRepositoriesCache
import dagger.Module
import dagger.Provides

@Module
class RoomGithubRepositoriesCacheModule {

    @Provides
    fun provideRepositoriesCache(): RoomGithubRepositoriesCache {
        return RoomGithubRepositoriesCache()
    }
}