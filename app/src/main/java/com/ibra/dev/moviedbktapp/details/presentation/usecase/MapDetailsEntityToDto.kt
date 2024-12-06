package com.ibra.dev.moviedbktapp.details.presentation.usecase

import com.ibra.dev.moviedbktapp.details.data.entities.DetailsMovieResponse
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel

interface MapDetailsEntityToDto {

    fun invoke(entity: DetailsMovieResponse): DetailsMovieModel
}