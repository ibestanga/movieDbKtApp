package com.ibra.dev.moviedbktapp.details.presentation.states

import com.ibra.dev.moviedbktapp.details.domain.models.DetailsMovieDto

sealed class DetailsMovieActions {

    data object Init : DetailsMovieActions()
    data object Loading : DetailsMovieActions()
    data class Error(val errorMsg: String) : DetailsMovieActions()
    data class GetDetailsSuccess(val data: DetailsMovieDto) : DetailsMovieActions()
}
