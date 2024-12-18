package com.ibra.dev.moviedbktapp.details.presentation.usecase

import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import kotlinx.coroutines.flow.Flow

interface GetDetailsUseCase {

    suspend fun invoke(movieId: Int): Flow<DetailsMovieModel>
}