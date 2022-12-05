package com.example.myapplicationmvp.presenter

import com.example.myapplicationmvp.utils.*
import com.example.myapplicationmvp.model.CountersModel
import com.example.myapplicationmvp.view.MainView

class CountersPresenter(private val view: MainView, private val model: CountersModel) {

    fun onCounterClick(id: String) {
        when (id) {
            FIRST_BUTTON -> {
                view.setText(model.next(FIRST_INDEX).toString(), FIRST_VIEW)
            }
            SECOND_BUTTON -> {
                view.setText(model.next(SECOND_INDEX).toString(), SECOND_VIEW)
            }
            THIRD_BUTTON -> {
                view.setText(model.next(THIRD_INDEX).toString(), THIRD_VIEW)
            }
        }
    }
}