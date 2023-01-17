package com.example.myapplicationmvp.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.adapters.UserRepoAdapter
import com.example.myapplicationmvp.core.BackPressedListener
import com.example.myapplicationmvp.core.networck.NetworkProviderRepos
import com.example.myapplicationmvp.core.utils.ARGS_KEY
import com.example.myapplicationmvp.core.utils.makeGone
import com.example.myapplicationmvp.core.utils.makeInvisible
import com.example.myapplicationmvp.core.utils.makeVisible
import com.example.myapplicationmvp.databinding.FragmentUserDataBinding
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import com.example.myapplicationmvp.model.database.RoomGithubRepositoriesCache
import com.example.myapplicationmvp.model.database.RoomGithubUsersCache
import com.example.myapplicationmvp.model.reposytories.impl.GithubRepositoryImpl
import com.example.myapplicationmvp.presenter.UserDataPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.util.*

class UserDataFragment : MvpAppCompatFragment(), TransferData, BackPressedListener {

    companion object {
        fun getInstance(user: GithubUser) = UserDataFragment().apply {
            arguments = Bundle().also { it.putParcelable(ARGS_KEY, user) }
        }
    }
    private val userDataAdapter = UserRepoAdapter()

    private val user by lazy { arguments?.getParcelable<GithubUser>(ARGS_KEY) as GithubUser }

    private val presenter: UserDataPresenter by moxyPresenter {
        UserDataPresenter(user, GithubRepositoryImpl(NetworkProviderRepos(user)
            .usersApi,App.getApp().database.userDao()
            ,App.getApp().database.reposDao()
            , RoomGithubRepositoriesCache()
            , RoomGithubUsersCache()
            ,App.getApp().getConnectSingle()), App.getApp().router)
    }
    private var _binding: FragmentUserDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            reposRecycle.layoutManager = LinearLayoutManager(requireContext())
            reposRecycle.adapter = userDataAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun transferData(user: GithubUser) {
        val name = "User - ${user.login.uppercase(Locale.ROOT)}"
        val avatar = user.avatarUrl
        val repository = "Repositories:"

        binding.apply {
            imageViewUserData.load(avatar)
            userDataNameTextView.text = name
            userDataRepoTextView.text = repository
        }
    }

    override fun getRepos(list: List<Repo>) {
        userDataAdapter.repos = list
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun startLoading() {
        binding.apply {
            progressBarUserDataFragment.makeVisible()
            imageViewUserData.makeInvisible()
            userDataNameTextView.makeInvisible()
            userDataRepoTextView.makeInvisible()
        }
    }

    override fun stopLoading() {
        binding.apply {
            progressBarUserDataFragment.makeGone()
            imageViewUserData.makeVisible()
            userDataNameTextView.makeVisible()
            userDataRepoTextView.makeVisible()
        }
    }
}