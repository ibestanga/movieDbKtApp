package com.ibra.dev.moviedbktapp.home.domain.usecase

import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.domain.repositories.HomeRepository
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetFavoritesMoviesUseCase
import com.ibra.dev.moviedbktapp.home.presentation.usecases.MapMovieEntityToDomainModelUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoritesMoviesImpl(
    private val repository: HomeRepository,
    private val mapMovieEntityToDomainModel: MapMovieEntityToDomainModelUseCase
) : GetFavoritesMoviesUseCase {
    override suspend fun invoke(): Flow<List<MovieDto>> {
        return repository.getFavoriteMovies().map { movies ->
            movies.map { item ->
                mapMovieEntityToDomainModel.invoke(item)
            }
        }
    }
}