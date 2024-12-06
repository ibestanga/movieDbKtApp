package com.ibra.dev.moviedbktapp.home.domain.usecase

import com.ibra.dev.moviedbktapp.commons.utils.orAlternative
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto
import com.ibra.dev.moviedbktapp.home.presentation.usecases.MapMovieEntityToDomainModel

class MapMovieEntityToDomainModelImpl : MapMovieEntityToDomainModel {

    override fun invoke(movieEntity: MovieEntity): MovieDto = MovieDto(
        id = movieEntity.id.orAlternative(0),
        name = movieEntity.title.orEmpty(),
        poster = movieEntity.posterPath.orEmpty(),
        overview = movieEntity.overview.orEmpty(),
        releaseDate = movieEntity.releaseDate.orEmpty(),
        voteAverage = movieEntity.voteAverage.orAlternative(0.0)
    )
}