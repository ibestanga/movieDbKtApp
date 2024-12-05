package com.ibra.dev.moviedbktapp.home.data.dao

import androidx.room.Insert
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity

interface HomeDao {

    @Insert
    fun insertMovies(movies: List<MovieEntity>)
}