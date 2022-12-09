package com.example.myapplicationmvp.view.fragments

import com.example.myapplicationmvp.model.data.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {

    fun initList(list: List<GithubUser>)
}