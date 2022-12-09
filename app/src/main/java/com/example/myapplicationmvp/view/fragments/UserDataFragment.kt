package com.example.myapplicationmvp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.R
import com.example.myapplicationmvp.core.BackPressedListener
import com.example.myapplicationmvp.databinding.FragmentUserDataBinding
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.presenter.DataPresenter
import com.example.myapplicationmvp.utils.ARGS_KEY
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDataFragment : MvpAppCompatFragment(), TransferData, BackPressedListener {

    companion object {
        fun getInstance(user: GithubUser) = UserDataFragment().apply {
            arguments = Bundle().also { it.putParcelable(ARGS_KEY, user) }
        }
    }
    private val user by lazy { arguments?.getParcelable<GithubUser>(ARGS_KEY) }

    private val presenter: DataPresenter by moxyPresenter { DataPresenter(user, App.getApp().router) }
    private var _binding: FragmentUserDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun transferData(user: GithubUser) {
        val name = "Name - ${user.name}"
        val age = "Age - ${user.age}"

        when(user.login) {
            "MrFoxy" -> { binding.imageViewUserData.setImageResource(R.drawable.girl2) }
            "Wednesday" -> {binding.imageViewUserData.setImageResource(R.drawable.wednesday)}
            "Vini_S_Puhoj" -> { binding.imageViewUserData.setImageResource(R.drawable.girl) }
            "JAzeMat" -> { binding.imageViewUserData.setImageResource(R.drawable.girl3) }
            "Salt" -> {binding.imageViewUserData.setImageResource(R.drawable.girl4)}
            else -> {binding.imageViewUserData.setImageResource(R.drawable.jely_fish)}
        }

        binding.userDataNameTextView.text = name
        binding.userDataAgeTextView.text = age
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }
}