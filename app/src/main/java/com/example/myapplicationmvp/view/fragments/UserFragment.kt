package com.example.myapplicationmvp.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.adapters.UserAdapter
import com.example.myapplicationmvp.core.BackPressedListener
import com.example.myapplicationmvp.core.navigation.UsersData
import com.example.myapplicationmvp.core.utils.makeGone
import com.example.myapplicationmvp.core.utils.makeVisible
import com.example.myapplicationmvp.databinding.FragmentUserBinding
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import com.example.myapplicationmvp.presenter.UserPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.io.*

class UserFragment : MvpAppCompatFragment(), UserView, BackPressedListener, TransferData {

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    private val userAdapter = UserAdapter(this)

    private val presenter: UserPresenter by moxyPresenter { UserPresenter() }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
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

    override fun startLoading() {
        binding.progressBarUserFragment.makeVisible()
    }

    override fun stopLoading() {
        binding.progressBarUserFragment.makeGone()
    }

    override fun transferData(user: GithubUser) {
        App.instance.router.navigateTo(UsersData(user))
    }

    override fun getRepos(list: List<Repo>) {}
}

