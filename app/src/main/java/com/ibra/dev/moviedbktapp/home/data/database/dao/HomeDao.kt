package com.ibra.dev.moviedbktapp.home.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel

@Dao
interface HomeDao {

    @Query("SELECT * FROM favorites_movies")
    suspend fun getFavoritesMovies(): List<DetailsMovieModel>

}