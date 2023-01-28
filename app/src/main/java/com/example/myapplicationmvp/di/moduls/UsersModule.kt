package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.model.database.MvpAppDatabase
import com.example.myapplicationmvp.model.database.UserDao
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class UsersModule {

    @Provides
    fun provideUserDao(database: MvpAppDatabase): UserDao {
        return database.userDao()
    }
}