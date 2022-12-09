package com.example.myapplicationmvp.presenter

import com.example.myapplicationmvp.core.navigation.UsersScreens
import com.example.myapplicationmvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(private val router: Router): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreens)
    }

    fun onBackPressed() {
        router.exit()
    }
}