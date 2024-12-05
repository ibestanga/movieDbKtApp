package com.ibra.dev.moviedbktapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ibra.dev.moviedbktapp.home.data.database.converter.GenreIdsConverter
import com.ibra.dev.moviedbktapp.home.data.database.dao.HomeDao
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(GenreIdsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao
}