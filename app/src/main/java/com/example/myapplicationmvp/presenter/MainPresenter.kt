package com.example.myapplicationmvp.presenter

import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.core.navigation.UsersScreens
import com.example.myapplicationmvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject


class MainPresenter: MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    init {
        App.instance.diContainer.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreens)
    }

    fun onBackPressed() {
        router.exit()
    }
}