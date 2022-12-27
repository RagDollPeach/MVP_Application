package com.example.myapplicationmvp.model.database

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "repos",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RepoObject(
    val id: Long,
    val name: String,
    val forks: Int,
    val userId: Long
)
