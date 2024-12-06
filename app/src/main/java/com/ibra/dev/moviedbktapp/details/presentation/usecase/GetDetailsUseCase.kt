package com.ibra.dev.moviedbktapp.details.presentation.usecase

import com.ibra.dev.moviedbktapp.details.data.entities.response.DetailsMovieResponse
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieDto
import kotlinx.coroutines.flow.Flow

interface GetDetailsUseCase {

    suspend fun invoke(movieId: Int): Flow<DetailsMovieDto>
}