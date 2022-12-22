package com.example.myapplicationmvp.model.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    @Expose
    @SerializedName("id") val id: String,
    @Expose
    @SerializedName("name") val name: String,
    @SerializedName("forks_count") val forksCount: String,
) : Parcelable