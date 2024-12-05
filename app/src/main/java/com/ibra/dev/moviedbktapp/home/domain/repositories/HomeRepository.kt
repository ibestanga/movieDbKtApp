package com.ibra.dev.moviedbktapp.home.domain.repositories

import androidx.paging.PagingData
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getPopularMovies(): Flow<PagingData<MovieEntity>>
}