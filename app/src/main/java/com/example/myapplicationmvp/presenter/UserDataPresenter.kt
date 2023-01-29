package com.example.myapplicationmvp.presenter

import android.util.Log
import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import com.example.myapplicationmvp.core.utils.disposeBy
import com.example.myapplicationmvp.di.custom.DaggerAppComponent
import com.example.myapplicationmvp.view.fragments.TransferData
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UserDataPresenter(
    private val user: GithubUser?
) : MvpPresenter<TransferData>() {

    @Inject
    lateinit var repository: GithubRepository

    @Inject
    lateinit var router: Router

    private val bag = CompositeDisposable()

    init {
        App.instance.diContainer = DaggerAppComponent
            .builder()
            .user(user)
            .build()

        App.instance.diContainer.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.startLoading()
        if (user != null) {
            repository.getUserById(user.login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.transferData(it)
                    viewState.stopLoading()
                },
                    { Log.e("@@@","some thing went wrong in UserDataPresenter, getUsersById method")})
                .disposeBy(bag)
        }
        if (user != null) {
            repository.getAllRepos(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.transferData(user)
                    viewState.getRepos(it)
                    viewState.stopLoading()
                },
                    {Log.e("@@@","some thing went wrong in UserDataPresenter, getAllRepos method")})
                .disposeBy(bag)
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}