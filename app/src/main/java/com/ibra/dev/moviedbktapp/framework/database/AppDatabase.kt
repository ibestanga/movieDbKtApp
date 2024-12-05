package com.ibra.dev.moviedbktapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibra.dev.moviedbktapp.home.data.dao.HomeDao

@Database(entities = [], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao
}