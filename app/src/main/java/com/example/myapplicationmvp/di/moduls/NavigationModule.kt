package com.example.myapplicationmvp.di.moduls

import com.example.myapplicationmvp.App
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    fun provideRouter(): Router {
        return App.instance.router
    }
}