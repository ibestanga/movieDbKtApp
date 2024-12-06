package com.ibra.dev.moviedbktapp.details.domain.usecase

import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import com.ibra.dev.moviedbktapp.details.presentation.usecase.SaveMovieLikeFavoriteUseCase

class SaveMovieLikeFavoriteUseCaseImpl(
    private val repository: DetailsMovieRepository
): SaveMovieLikeFavoriteUseCase {
    override suspend fun invoke(movie: DetailsMovieModel) {
        return repository.saveFavoriteMovie(movie)
    }
}