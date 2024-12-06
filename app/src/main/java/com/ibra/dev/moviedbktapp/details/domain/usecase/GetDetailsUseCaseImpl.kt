package com.ibra.dev.moviedbktapp.details.domain.usecase

import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieDto
import com.ibra.dev.moviedbktapp.details.domain.repositories.DetailsMovieRepository
import com.ibra.dev.moviedbktapp.details.presentation.usecase.GetDetailsUseCase
import com.ibra.dev.moviedbktapp.details.presentation.usecase.MapDetailsEntityToDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDetailsUseCaseImpl(
    private val repository: DetailsMovieRepository,
    private val mapDto: MapDetailsEntityToDto
): GetDetailsUseCase {

    override suspend fun invoke(movieId: Int): Flow<DetailsMovieDto> {
        return repository.getDetailMovie(movieId).map { response ->
            mapDto.invoke(response)
        }
    }
}