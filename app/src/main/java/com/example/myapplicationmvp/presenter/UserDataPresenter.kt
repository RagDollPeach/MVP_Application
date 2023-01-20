package com.example.myapplicationmvp.presenter

import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import com.example.myapplicationmvp.core.utils.disposeBy
import com.example.myapplicationmvp.view.fragments.TransferData
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserDataPresenter(
    private val user: GithubUser?,
    private val repository: GithubRepository,
    private val router: Router
) : MvpPresenter<TransferData>(){

    private val bag = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.startLoading()
        if (user != null) {
            repository.getUserById(user.login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.transferData(it)
                    viewState.stopLoading() },
                    {})
                .disposeBy(bag)
        }

        repository.getAllRepos(user!!)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.transferData(user)
                viewState.getRepos(it)
                viewState.stopLoading() },
                {})
            .disposeBy(bag)
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