package com.example.myapplicationmvp.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDto(
    @Expose
    @SerializedName("id") val id: String,
    @Expose
    @SerializedName("login") val login: String,
    @Expose
    @SerializedName("avatar_url") val avatarUrl: String,
    @Expose
    @SerializedName("repos_url") val repos: String
) : Parcelable
