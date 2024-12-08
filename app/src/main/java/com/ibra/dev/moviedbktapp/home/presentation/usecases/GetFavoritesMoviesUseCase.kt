package com.ibra.dev.moviedbktapp.home.presentation.usecases

import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import kotlinx.coroutines.flow.Flow

interface GetFavoritesMoviesUseCase {

    suspend fun invoke():Flow<List<MovieDto>>
}