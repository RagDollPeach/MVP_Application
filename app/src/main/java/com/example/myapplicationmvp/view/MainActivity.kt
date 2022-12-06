package com.example.myapplicationmvp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        pressButtonOne()
        pressButtonTwo()
        pressButtonTree()
    }

    private fun pressButtonOne() {
        binding.button1.setOnClickListener {
            presenter.onCounterClickFirstButton()
        }
    }

    private fun pressButtonTwo() {
        binding.button2.setOnClickListener {
            presenter.onCounterClickSecondButton()
        }
    }

    private fun pressButtonTree() {
        binding.button3.setOnClickListener {
            presenter.onCounterClickThirdButton()
        }
    }

    private fun initPresenter() {
        presenter = CountersPresenter(this, CountersModel())
    }

    override fun setTextOnFirstView(counter: String) {
        binding.textView1.text = counter
    }

    override fun setTextOnSecondView(counter: String) {
        binding.textView2.text = counter
    }

    override fun setTextOnThirdView(counter: String) {
        binding.textView3.text = counter
    }
}