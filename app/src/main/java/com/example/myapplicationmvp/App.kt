package com.example.myapplicationmvp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.myapplicationmvp.model.database.MvpAppDatabase
import com.example.myapplicationmvp.core.utils.ConnectivityListener
import com.example.myapplicationmvp.di.custom.DaggerDiContainer
import com.example.myapplicationmvp.di.custom.DiContainer
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router


class App: Application() {

    private lateinit var connectivityListener: ConnectivityListener

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    val database: MvpAppDatabase by lazy { MvpAppDatabase.create(this) }

    lateinit var diContainer: DiContainer

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        diContainer = DaggerDiContainer
            .builder()
            .user(user = null)
            .build()

        connectivityListener = ConnectivityListener(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

    fun getConnectObservable() = connectivityListener.status()

    fun getConnectSingle() = connectivityListener.statusSingle()
}