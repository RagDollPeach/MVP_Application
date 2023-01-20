package com.example.myapplicationmvp.di

import com.example.myapplicationmvp.presenter.MainPresenter
import com.example.myapplicationmvp.view.MainActivity
import dagger.Component

@Component
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}