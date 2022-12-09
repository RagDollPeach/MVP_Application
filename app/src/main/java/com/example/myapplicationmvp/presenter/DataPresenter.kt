package com.example.myapplicationmvp.presenter

import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.view.fragments.TransferData
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DataPresenter(private val user: GithubUser?, private val router: Router) :
    MvpPresenter<TransferData>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (user != null) {
            viewState.transferData(user)
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}