package com.example.myapplicationmvp.presenter

import android.util.Log
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import com.example.myapplicationmvp.view.fragments.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(private val repository: GithubRepository
,private val router : Router): MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.startLoading()
        repository.getUsers()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { viewState.initList(it)
            viewState.stopLoading()},
            { Log.e("@","some thing went wrong") })

    }

    fun onBackPressed() : Boolean {
        router.exit()
        return true
    }
}