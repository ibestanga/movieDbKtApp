package com.ibra.dev.moviedbktapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ibra.dev.moviedbktapp.details.data.database.converter.MovieConverters
import com.ibra.dev.moviedbktapp.details.data.database.dao.DetailsMoviesDao
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.home.data.database.dao.HomeDao

@Database(entities = [DetailsMovieModel::class], version = 2)
@TypeConverters(MovieConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao
    abstract fun detailsDao(): DetailsMoviesDao
}