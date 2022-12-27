package com.example.myapplicationmvp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class MvpAppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun create(context: Context): MvpAppDatabase {
            return Room.databaseBuilder(
                context, MvpAppDatabase::class.java, "mvp_app_database").build()
        }
    }
}
