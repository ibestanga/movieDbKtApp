package com.ibra.dev.moviedbktapp.details.domain.usecase

import com.ibra.dev.moviedbktapp.commons.utils.orAlternative
import com.ibra.dev.moviedbktapp.details.data.entities.DetailsMovieResponse
import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieModel

class MapDetailsEntityToDtoImpl : MapDetailsEntityToDto {
    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500%s"
    }

    override fun invoke(entity: DetailsMovieResponse): DetailsMovieModel {
        return DetailsMovieModel(
            id = entity.id.orAlternative(),
            title = entity.title.orEmpty(),
            releaseDate = entity.releaseDate.orEmpty(),
            genres = entity.genres?.map { it.name.orEmpty() }.orEmpty(),
            voteAverage = entity.voteAverage.orAlternative(0.0),
            tagLine = entity.tagline.orEmpty(),
            overview = entity.overview.orEmpty(),
            popularity = entity.popularity.orAlternative(0.0),
            language = entity.spokenLanguages?.map { it.iso6391.orEmpty() }.orEmpty(),
            backdropPoster = String.format(IMAGE_BASE_URL, entity.backdropPath.orEmpty()),
            poster = String.format(IMAGE_BASE_URL, entity.posterPath.orEmpty()),
            productionCompanyNames = entity.productionCompanies?.map { it.name.orEmpty() }.orEmpty()
        )
    }
}