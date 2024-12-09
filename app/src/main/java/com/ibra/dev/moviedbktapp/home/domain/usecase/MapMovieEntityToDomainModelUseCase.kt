package com.ibra.dev.moviedbktapp.home.domain.usecase

import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto

interface MapMovieEntityToDomainModelUseCase {

    fun invoke(movieEntity: MovieEntity): MovieDto

    fun invoke(favoriteMovieModel:DetailsMovieModel): MovieDto
}