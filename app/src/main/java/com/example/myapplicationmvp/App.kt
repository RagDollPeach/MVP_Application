package com.example.myapplicationmvp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.myapplicationmvp.model.database.MvpAppDatabase
import com.example.myapplicationmvp.core.utils.ConnectivityListener
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App: Application() {

    private lateinit var connectivityListener: ConnectivityListener
  //  private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

      //  appComponent = DaggerAppComponent.create()

        connectivityListener = ConnectivityListener(
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

        RxJavaPlugins.setErrorHandler {  }
    }

    companion object {
        private var instance: App? = null
        fun getApp() = instance!!
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    val database: MvpAppDatabase by lazy { MvpAppDatabase.create(this) }

    fun getConnectObservable() = connectivityListener.status()
    fun getConnectSingle() = connectivityListener.statusSingle()
}