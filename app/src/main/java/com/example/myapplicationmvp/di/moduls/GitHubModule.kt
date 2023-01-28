package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.model.database.ReposDAO
import com.example.myapplicationmvp.model.database.RoomGithubRepositoriesCache
import com.example.myapplicationmvp.model.database.RoomGithubUsersCache
import com.example.myapplicationmvp.model.database.UserDao
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import com.example.myapplicationmvp.model.reposytories.impl.GithubRepositoryImpl
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        DatabaseModule::class, NavigationModule::class, UsersModule::class,
        ReposModule::class, ApiModule::class, RoomGithubUsersCacheModule::class,
        RoomGithubRepositoriesCacheModule::class
    ]
)
class GitHubModule {

    @Provides
    fun provideGithubRepository(
        usersApi: UsersApi,
        usersDao: UserDao,
        reposDAO: ReposDAO,
        repositoriesCache: RoomGithubRepositoriesCache,
        usersCache: RoomGithubUsersCache
    ): GithubRepository {
        return GithubRepositoryImpl(
            usersApi,
            usersDao,
            reposDAO,
            repositoriesCache,
            usersCache
        )
    }
}