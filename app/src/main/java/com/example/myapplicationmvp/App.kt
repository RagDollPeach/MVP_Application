package com.example.myapplicationmvp

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun getApp() = instance!!
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
}