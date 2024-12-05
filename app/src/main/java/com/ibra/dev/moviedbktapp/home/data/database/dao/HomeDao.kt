package com.ibra.dev.moviedbktapp.home.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity

@Dao
interface HomeDao {

    @Insert
    fun insertMovies(movies: List<MovieEntity>)
}