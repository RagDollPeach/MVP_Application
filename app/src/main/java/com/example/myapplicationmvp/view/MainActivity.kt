package com.example.myapplicationmvp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationmvp.utils.*
import com.example.myapplicationmvp.databinding.ActivityMainBinding
import com.example.myapplicationmvp.model.CountersModel
import com.example.myapplicationmvp.presenter.CountersPresenter

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: CountersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPresenter()

        with(binding) {
            button1.setOnClickListener {
                presenter.onCounterClick(FIRST_BUTTON)
            }
            button2.setOnClickListener {
                presenter.onCounterClick(SECOND_BUTTON)
            }
            button3.setOnClickListener {
                presenter.onCounterClick(THIRD_BUTTON)
            }
        }
    }

    private fun initPresenter() {
        presenter = CountersPresenter(this, CountersModel())
    }

    override fun setText(counter: String, position: String) {
        with(binding) {
            when (position) {
                FIRST_VIEW -> textView1.text = counter
                SECOND_VIEW -> textView2.text = counter
                THIRD_VIEW -> textView3.text = counter
            }
        }
    }
}