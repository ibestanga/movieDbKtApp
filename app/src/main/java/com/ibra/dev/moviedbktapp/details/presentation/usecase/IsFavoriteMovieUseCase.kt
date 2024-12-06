package com.ibra.dev.moviedbktapp.details.presentation.usecase

interface IsFavoriteMovieUseCase {

    suspend fun invoke(movieId: Int): Boolean
}