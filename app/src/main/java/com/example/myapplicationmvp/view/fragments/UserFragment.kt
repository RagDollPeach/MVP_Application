package com.example.myapplicationmvp.view.fragments

import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationmvp.App
import com.example.myapplicationmvp.R
import com.example.myapplicationmvp.adapters.UserAdapter
import com.example.myapplicationmvp.core.BackPressedListener
import com.example.myapplicationmvp.core.navigation.UsersData
import com.example.myapplicationmvp.databinding.FragmentUserBinding
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.reposytories.impl.GithubRepositoryImpl
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

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubRepositoryImpl(), App.getApp().router)
    }

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

    override fun onButtonPressed(picture: String) {
        binding.buttonTest.setOnClickListener {
            val localPath = "${App.getApp().filesDir}/assets"
            val inputStream = requireContext().assets.open(picture)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            try {
                val file = File(localPath).apply { createNewFile() }
                FileOutputStream(file).also {
                    bitmap.compress(CompressFormat.PNG, 100, it)
                    it.flush()
                    it.close()
                }
            } catch (e: IOException) {
                e.stackTrace
            }
        }
    }

    override fun showPicture() {
        binding.buttonTest.setOnClickListener {
            binding.imageViewTest.setImageResource(R.drawable.girl)
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

    override fun transferData(user: GithubUser) {
        App.getApp().router.navigateTo(UsersData(user))
    }
}

