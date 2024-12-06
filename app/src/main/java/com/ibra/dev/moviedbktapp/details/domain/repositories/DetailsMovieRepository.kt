package com.ibra.dev.moviedbktapp.details.domain.repositories

import com.ibra.dev.moviedbktapp.details.data.entities.DetailsMovieResponse
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import kotlinx.coroutines.flow.Flow

interface DetailsMovieRepository {

    suspend fun getDetailMovie(movieId: Int) : Flow<DetailsMovieResponse>

    suspend fun isFavoriteMovie(movieId: Int) : Boolean

    suspend fun deleteFromFavoriteMovies(movieId: Int)

    suspend fun saveFavoriteMovie(movie: DetailsMovieModel)
}