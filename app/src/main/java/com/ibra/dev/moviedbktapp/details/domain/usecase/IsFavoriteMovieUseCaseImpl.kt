package com.ibra.dev.moviedbktapp.details.domain.usecase

import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import com.ibra.dev.moviedbktapp.details.presentation.usecase.IsFavoriteMovieUseCase

class IsFavoriteMovieUseCaseImpl(
    private val repository: DetailsMovieRepository
) : IsFavoriteMovieUseCase {
    override suspend fun invoke(movieId: Int): Boolean = repository.isFavoriteMovie(movieId)
}