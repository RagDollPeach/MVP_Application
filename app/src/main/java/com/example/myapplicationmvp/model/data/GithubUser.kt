package com.example.myapplicationmvp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val reposPath: String
) : Parcelable
