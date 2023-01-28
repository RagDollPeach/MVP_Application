package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.model.database.MvpAppDatabase
import com.example.myapplicationmvp.model.database.ReposDAO
import dagger.Module
import dagger.Provides

@Module
class ReposModule {

    @Provides
    fun provideReposDao(database: MvpAppDatabase): ReposDAO {
        return database.reposDao()
    }
}