package com.ibra.dev.moviedbktapp.details.presentation.usecase

import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel

interface SaveMovieLikeFavoriteUseCase {

    suspend fun invoke(movie: DetailsMovieModel)
}