package com.ibra.dev.moviedbktapp.details.presentation.usecase

interface DeleteMovieFromFavorites {

    suspend fun invoke(movieId: Int)
}