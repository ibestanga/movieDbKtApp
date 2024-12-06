package com.ibra.dev.moviedbktapp.details.domain.usecase

import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import com.ibra.dev.moviedbktapp.details.presentation.usecase.DeleteMovieFromFavorites

class DeleteMovieFromFavoritesImpl(
    private val repository: DetailsMovieRepository
) : DeleteMovieFromFavorites {
    override suspend fun invoke(movieId: Int) {
        repository.deleteFromFavoriteMovies(movieId)
    }
}