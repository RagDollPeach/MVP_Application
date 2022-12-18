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
        repository.getUsers()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { viewState.initList(it)},
            { Log.e("@","some thing went wrong") })

        repository.getPicture()
            .subscribeOn(Schedulers.newThread())
            .doOnSuccess { viewState.onButtonPressed(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.showPicture()  },
                { Log.e("@","wrong picture") })
    }

    fun onBackPressed() : Boolean {
        router.exit()
        return true
    }
}