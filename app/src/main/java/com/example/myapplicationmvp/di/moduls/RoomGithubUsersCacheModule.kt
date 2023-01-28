package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.model.database.RoomGithubUsersCache
import dagger.Module
import dagger.Provides

@Module
class RoomGithubUsersCacheModule {

    @Provides
    fun provideUsersCache(): RoomGithubUsersCache {
        return RoomGithubUsersCache()
    }
}