package com.ibra.dev.moviedbktapp.details.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel

@Dao
interface DetailsMoviesDao {

    @Query("SELECT * FROM favorites_movies WHERE id = :movieId ")
    fun getMovieById(movieId: Int): DetailsMovieModel?

    @Insert
    fun saveMovieLikeFavorite(movie: DetailsMovieModel)

    @Query("DELETE FROM favorites_movies WHERE id = :movieId")
    fun deleteMovieLikeFavorite(movieId: Int)
}