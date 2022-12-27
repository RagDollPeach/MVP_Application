package com.example.myapplicationmvp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val reposPath: String
)