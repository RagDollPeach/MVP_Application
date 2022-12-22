package com.example.myapplicationmvp.view.fragments

import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TransferData: MvpView {

    fun transferData(user: GithubUser)
    fun getRepos(list: List<Repo>)
    fun startLoading()
    fun stopLoading()
}