package com.example.myapplicationmvp.model.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userEntity: List<UserEntity>): Completable

    @Query("SELECT * FROM users")
    fun queryForAllUsers(): Single<List<UserEntity>>

    @Delete
    fun delete(userEntity: UserEntity): Completable
}