package com.ibra.dev.moviedbktapp.home.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.domain.repositories.HomeRepository
import com.ibra.dev.moviedbktapp.home.presentation.usecases.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopularMoviesUseCaseImpl(
    private val repository: HomeRepository,
    private val mapEntityToDto: MapMovieEntityToDomainModelUseCase
) : GetPopularMoviesUseCase {
    override suspend fun invoke(): Flow<PagingData<MovieDto>> {
        return repository.getPopularMovies().map { movies ->
            movies.map { entity ->
                mapEntityToDto.invoke(entity)
            }
        }
    }
}