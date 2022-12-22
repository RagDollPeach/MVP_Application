package com.example.myapplicationmvp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationmvp.R
import com.example.myapplicationmvp.model.data.Repo

class UserRepoAdapter: RecyclerView.Adapter<UserRepoAdapter.UserRepoViewHolder>() {

    var repos: List<Repo> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserRepoAdapter.UserRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repos_item, parent, false)
        return UserRepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int {
      return repos.size
    }

   inner class UserRepoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

       private val _repo by lazy { itemView.findViewById<TextView>(R.id.repos_text_view) }

       fun bind(repo: Repo) {
           _repo.text = repo.name
           _repo.setOnClickListener {
               Toast.makeText(itemView.context, "Forks - ${repo.forksCount}",Toast.LENGTH_SHORT).show() }
       }
   }
}