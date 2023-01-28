package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.model.database.MvpAppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(): MvpAppDatabase {
        return App.instance.database
    }
}