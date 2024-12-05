package com.ibra.dev.moviedbktapp.home.presentation.usecases

import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto

interface MapMovieEntityToDomainModel {

    fun invoke(movieEntity: MovieEntity): MovieDto
}