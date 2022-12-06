package com.example.myapplicationmvp.presenter

import com.example.myapplicationmvp.utils.*
import com.example.myapplicationmvp.model.CountersModel
import com.example.myapplicationmvp.view.MainView

class CountersPresenter(private val view: MainView, private val model: CountersModel) {

    fun onCounterClickFirstButton() {
        view.setTextOnFirstView(model.next(FIRST_INDEX).toString())
    }
    fun onCounterClickSecondButton() {
        view.setTextOnSecondView(model.next(SECOND_INDEX).toString())
    }
    fun onCounterClickThirdButton() {
        view.setTextOnThirdView(model.next(THIRD_INDEX).toString())
    }
}