package com.example.myapplicationmvp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationmvp.R
import com.example.myapplicationmvp.model.data.GithubUser

class UserAdapter : RecyclerView.Adapter<UserAdapter.GithubUserViewHolder>() {

     var users: List<GithubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return GithubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val _login by lazy { itemView.findViewById<TextView>(R.id.login_textview_recycler_item) }

        fun bind(item: GithubUser) {
            _login.text = item.login
        }
    }
}

