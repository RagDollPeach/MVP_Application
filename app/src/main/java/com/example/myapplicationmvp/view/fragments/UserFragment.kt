package com.example.myapplicationmvp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.adapters.UserAdapter
import com.example.myapplicationmvp.core.BackPressedListener
import com.example.myapplicationmvp.databinding.FragmentUserBinding
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.reposytories.impl.GithubRepositoryImpl
import com.example.myapplicationmvp.presenter.UserPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackPressedListener {

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    private val userAdapter = UserAdapter()

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubRepositoryImpl(), App.getApp().router) }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            //recyclerView.setItemViewCacheSize(1)
            recyclerView.adapter = userAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun initList(list: List<GithubUser>) {
        userAdapter.users = list
    }
}

