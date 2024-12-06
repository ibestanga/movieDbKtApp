package com.ibra.dev.moviedbktapp.details.data.repositories

import com.ibra.dev.moviedbktapp.details.data.api.DetailsMoviesApi
import com.ibra.dev.moviedbktapp.details.data.entities.response.DetailsMovieResponse
import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class DetailsMovieRepositoryImpl(
    private val api: DetailsMoviesApi
) : DetailsMovieRepository {
    override suspend fun getDetailMovie(movieId: Int): Flow<DetailsMovieResponse> = flow {
        val response = api.getDetailMovie(movieId)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) } ?: throw IllegalStateException("something wrong happen")
        } else {
            throw HttpException(response)
        }
    }
}