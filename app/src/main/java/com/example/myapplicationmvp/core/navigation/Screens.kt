package com.example.myapplicationmvp.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.view.fragments.UserDataFragment
import com.example.myapplicationmvp.view.fragments.UserFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreens: FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserFragment.getInstance()
    }
}

data class UsersData(private val user: GithubUser): FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDataFragment.getInstance(user)
    }
}