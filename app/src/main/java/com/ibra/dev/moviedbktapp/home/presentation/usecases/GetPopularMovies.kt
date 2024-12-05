package com.ibra.dev.moviedbktapp.home.presentation.usecases

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import kotlinx.coroutines.flow.Flow

interface GetPopularMovies {

    suspend fun invoke(): Flow<PagingData<MovieDto>>
}