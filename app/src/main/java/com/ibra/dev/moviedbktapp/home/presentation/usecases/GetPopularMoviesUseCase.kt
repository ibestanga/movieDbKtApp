package com.ibra.dev.moviedbktapp.home.presentation.usecases

import androidx.paging.PagingData
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {

    suspend fun invoke(): Flow<PagingData<MovieDto>>
}