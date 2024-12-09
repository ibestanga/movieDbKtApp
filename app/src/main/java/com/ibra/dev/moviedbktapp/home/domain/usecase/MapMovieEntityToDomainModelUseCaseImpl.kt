package com.ibra.dev.moviedbktapp.home.domain.usecase

import com.ibra.dev.moviedbktapp.commons.utils.orAlternative
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel
import com.ibra.dev.moviedbktapp.home.data.entities.MovieEntity
import com.ibra.dev.moviedbktapp.home.domain.models.MovieDto

class MapMovieEntityToDomainModelUseCaseImpl : MapMovieEntityToDomainModelUseCase {

    override fun invoke(movieEntity: MovieEntity): MovieDto = MovieDto(
        id = movieEntity.id.orAlternative(),
        name = movieEntity.title.orEmpty(),
        poster = movieEntity.posterPath.orEmpty(),
        overview = movieEntity.overview.orEmpty(),
        releaseDate = movieEntity.releaseDate.orEmpty(),
        voteAverage = movieEntity.voteAverage.orAlternative()
    )

    override fun invoke(favoriteMovieModel: DetailsMovieModel): MovieDto = MovieDto(
        id = favoriteMovieModel.id.orAlternative(),
        name = favoriteMovieModel.title,
        poster = favoriteMovieModel.poster,
        overview = favoriteMovieModel.overview,
        releaseDate = favoriteMovieModel.releaseDate,
        voteAverage = favoriteMovieModel.voteAverage.orAlternative()
    )
}