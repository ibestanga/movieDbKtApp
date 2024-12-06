package com.ibra.dev.moviedbktapp.details.presentation.usecase

import com.ibra.dev.moviedbktapp.details.data.entities.response.DetailsMovieResponse
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieDto

interface MapDetailsEntityToDto {

    fun invoke(entity: DetailsMovieResponse): DetailsMovieDto
}