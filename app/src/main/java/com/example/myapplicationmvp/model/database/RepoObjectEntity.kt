package com.example.myapplicationmvp.model.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "repos",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RepoObjectEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val forks: Int,
    val userId: Long
)
