package com.ibra.dev.moviedbktapp.home.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies ORDER BY popularity DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllMoviesSortedByPopularity(limit: Int, offset: Int): List<MovieEntity>
}