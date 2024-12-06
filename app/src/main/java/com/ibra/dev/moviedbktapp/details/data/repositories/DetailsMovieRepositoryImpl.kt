package com.ibra.dev.moviedbktapp.details.data.repositories

import com.ibra.dev.moviedbktapp.details.data.api.DetailsMoviesApi
import com.ibra.dev.moviedbktapp.details.data.database.dao.DetailsMoviesDao
import com.ibra.dev.moviedbktapp.details.data.entities.DetailsMovieResponse
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class DetailsMovieRepositoryImpl(
    private val remoteDataSource: DetailsMoviesApi,
    private val localDataSource: DetailsMoviesDao
) : DetailsMovieRepository {
    override suspend fun getDetailMovie(movieId: Int): Flow<DetailsMovieResponse> = flow {
        val response = remoteDataSource.getDetailMovie(movieId)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) } ?: throw IllegalStateException("something wrong happen")
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun isFavoriteMovie(movieId: Int): Boolean =
        localDataSource.getMovieById(movieId) != null

    override suspend fun saveFavoriteMovie(movie: DetailsMovieModel) =
        localDataSource.saveMovieLikeFavorite(movie)

    override suspend fun deleteFromFavoriteMovies(movieId: Int) {
        localDataSource.deleteMovieLikeFavorite(movieId)
    }
}