package com.ibra.dev.moviedbktapp.details.domain.repositories

import com.ibra.dev.moviedbktapp.details.data.entities.response.DetailsMovieResponse
import kotlinx.coroutines.flow.Flow

interface DetailsMovieRepository {

    suspend fun getDetailMovie(movieId: Int) : Flow<DetailsMovieResponse>
}