package com.example.myapplicationmvp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ReposDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRepos(repos: List<RepoObjectEntity>): Completable

    @Query("SELECT * FROM repos WHERE userId =:userId")
    fun queryForAllRepos(userId: String): Single<List<RepoObjectEntity>>
}